package com.web.rpg.service.world.impl;

import com.web.rpg.dao.CharacterDao;
import com.web.rpg.events.EventProcessorService;
import com.web.rpg.model.Characters.CharacterClass;
import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.EquipmentSlot;
import com.web.rpg.model.Items.impl.Item;
import com.web.rpg.model.Monsters.Monster;
import com.web.rpg.model.cities.City;
import com.web.rpg.service.character.CharacterService;
import com.web.rpg.service.cities.CityService;
import com.web.rpg.service.items.ItemService;
import com.web.rpg.service.monster.MonsterService;
import com.web.rpg.service.shared.util.HealingCharacterUtil;
import com.web.rpg.service.world.Event;
import com.web.rpg.service.world.WorldSchedulerService;
import com.web.rpg.service.world.util.ItemGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class WorldSchedulerSchedulerServiceImpl implements WorldSchedulerService {

    private final CharacterService characterService;
    private final HealingCharacterUtil healingCharacterUtil;
    private final MonsterService monsterService;
    private final ItemService itemService;
    private final ItemGenerator itemGenerator;
    private final CityService cityService;
    private final EventProcessorService eventProcessorService;

    @Autowired
    public WorldSchedulerSchedulerServiceImpl(CharacterDao characterDao,
                                              CharacterService characterService,
                                              HealingCharacterUtil healingCharacterUtil,
                                              MonsterService monsterService,
                                              ItemService itemService,
                                              ItemGenerator itemGenerator,
                                              CityService cityService,
                                              EventProcessorService eventProcessorService) {
        this.characterService = characterService;
        this.healingCharacterUtil = healingCharacterUtil;
        this.monsterService = monsterService;
        this.itemService = itemService;
        this.itemGenerator = itemGenerator;
        this.cityService = cityService;
        this.eventProcessorService = eventProcessorService;
    }

    private static final Random RANDOM = new Random();
    private static final List<Event> EVENTS = Arrays.asList(Event.values());

    @Override
    public void changeWorldStatement() {
        changeCharactersStatements();
    }

    @Override
    public void changeCharactersStatements() {
        List<PlayerCharacter> characters = characterService.findAll();
        if (characters != null && !characters.isEmpty()) {
            characters.forEach(this::action);
        }
    }

    private void action(PlayerCharacter character) {
        if (character.getCountToEndOfAction() == null || character.getCountToEndOfAction() < 1) {
            Event event = EVENTS.get(RANDOM.nextInt(EVENTS.size()));
            switch (event) {
                case STORY: {
                    processEvent(character);
                    break;
                }
                case FIGHT: {
                    fight(character);
                    break;
                }
                case SLEEP: {
                    sleep(character);
                    break;
                }
            }
        }
        else {
            continueAction(character);
        }
    }

    private void continueAction(PlayerCharacter character) {
        processFight(character);
    }

    private void sleep(PlayerCharacter character) {
        character.setHitPoints(character.getMaxHitPoints());
        character.setManaPoints(character.getMaxManaPoints());
        String currentAction = "I am sleep, wait";
        character.setCurrentAction(currentAction);
        characterService.save(character);
    }

    private void fight(PlayerCharacter character) {
        Monster monster = monsterService.prepearMonsterForBattle(character);
        character.setMonster(monster);
        monsterService.updateOrCreate(monster);
        String currentAction = "Fighting with " + monster.getName() +
                ", " +
                (int)monster.getHitPoint() + "hp";
        character.setCurrentAction(currentAction);
        character.setCountToEndOfAction(1);
        characterService.save(character);
    }

    private void processEvent(PlayerCharacter character) {
        travel(character);
        progressQuest(character);
        characterService.save(character);
    }

    private void progressQuest(PlayerCharacter character) {
        if (character.getQuest() == null || character.getStory() == null) {
            eventProcessorService.generateQuest(character);
        } else {
            if (character.getStory().isEnd()) {
                getRewards(character);
                character.setQuest(null);
            } else {
                eventProcessorService.changeEvent(character);
            }
        }

    }

    private void getRewards(PlayerCharacter character) {
        character.setExperience(character.getExperience() + character.getQuest().getExp());
        character.setGold(character.getGold() + character.getQuest().getGold());
    }

    private void travel(PlayerCharacter character) {
        if (character.getTargetCity() == null && character.getCurrentCity() != null) {
            List<City> cities = new ArrayList(character.getCurrentCity().getCitiesNear().keySet());
            City targetCity = cities.get(RANDOM.nextInt(cities.size()));
            character.setTargetCity(targetCity);
            character.setStepsToCity(character.getCurrentCity().getCitiesNear().get(targetCity));
            character.setCurrentCity(null);
        } else if (character.getTargetCity() == null && character.getCurrentCity() == null) {
            List<City> cities = cityService.findAll();
            City targetCity = cities.get(RANDOM.nextInt(cities.size()));
            character.setTargetCity(targetCity);
            character.setStepsToCity(42);
        } else {
            character.setStepsToCity(character.getStepsToCity() - 1);
            if (character.getStepsToCity() < 1) {
                character.setCurrentCity(character.getTargetCity());
                character.setTargetCity(null);
            }
        }
        findRandomGoods(character);
    }

    private void processFight(PlayerCharacter character) {
        if (CharacterClass.WIZZARD.equals(character.getCharacterClass())) {
            if (character.getManaPoints() > 0)
                character.setManaPoints(character.getManaPoints() - 50);
            else {
                if (autoHealManaPoints(character)) {
                    character.setManaPoints(character.getManaPoints() - 50);
                } else {
                    character.setCountToEndOfAction(0);
                    character.setMonster(null);
                    return;
                }
            }
        }

        if (character.getHitPoints() < character.getMaxHitPoints() / 2) {
            if (!autoHealHitPoints(character)) {
                character.setCountToEndOfAction(0);
                character.setMonster(null);
                return;
            }
        }

        Monster monster = character.getMonster();
        monster.setHitPoint((monster.getHitPoint() - (character.getBaseDamage())));
        character.setHitPoints((character.getHitPoints() + character.getDefence() - monster.getDamage()));
        if (monster.getHitPoint() <= 0) {
            character.setCountToEndOfAction(-1);
            IntStream.range(0, 100).mapToObj(i -> character).forEach(this::findRandomGoods);
            autoEquip(character, itemGenerator.generateItem(character));
            character.setMonster(null);
            character.setCurrentAction("Killed a " + monster.getName());
            monsterService.remove(monster);
        } else {
            monsterService.updateOrCreate(monster);
            character.setCurrentAction("Fighting with " + monster.getName() + ": " + monster.getHitPoint() + "hp");
        }
        characterService.save(character);
    }

    private void autoEquip(PlayerCharacter character, Item newItem) {
        Map<EquipmentSlot, Item> equipment = character.getItems()
                .stream().collect(Collectors.toMap(Item::getSlot, item -> item, (a, b) -> b));
        if (equipment.isEmpty() || !equipment.containsKey(newItem.getSlot())) {
            equipment.put(newItem.getSlot(), newItem);
            character.setItems(new ArrayList<>(equipment.values()));
            characterService.save(character);
        } else {
            if (newItem.getActivePoints() > equipment.get(newItem.getSlot()).getActivePoints()) {
                itemService.removeItem(equipment.get(newItem.getSlot()));
                itemService.updateOrCreate(newItem);
                equipment.put(newItem.getSlot(), newItem);
                character.setItems((List<Item>) equipment.values());
                characterService.save(character);
            }
        }
    }

    private void findRandomGoods(PlayerCharacter character) {
        int chance = RANDOM.nextInt(99);
        if (chance == 42) {
            character.setGold(character.getGold() + RANDOM.nextInt(10));
        } else if (chance == 47) {
            character.setCountOfBigManaPointBottles(character.getCountOfBigManaPointBottles() + 1);
        } else if (chance == 52) {
            character.setCountOfMiddleManaPointBottles(character.getCountOfMiddleManaPointBottles() + 1);
        } else if (chance == 29) {
            character.setCountOfSmallManaPointBottles(character.getCountOfSmallManaPointBottles() + 1);
        } else if (chance == 54) {
            character.setCountOfBigHitPointBottle(character.getCountOfBigHitPointBottle() + 1);
        } else if (chance == 21) {
            character.setCountOfMiddleHitPointBottle(character.getCountOfMiddleHitPointBottle() + 1);
        } else if (chance == 13) {
            character.setCountOfSmallHitPointBottle(character.getCountOfSmallHitPointBottle() + 1);
        }
    }

    private boolean autoHealHitPoints(PlayerCharacter character) {
        return healingCharacterUtil.autoHealHitPoints(character);
    }

    private boolean autoHealManaPoints(PlayerCharacter character) {
        return healingCharacterUtil.autoHealManaPoints(character);
    }
}