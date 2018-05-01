package com.web.rpg.service.world.impl;

import com.web.rpg.dao.CharacterDao;
import com.web.rpg.model.Characters.CharacterClass;
import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.EquipmentSlot;
import com.web.rpg.model.Items.impl.Item;
import com.web.rpg.model.Items.impl.heal.healHitPoint.items.BigHPBottle;
import com.web.rpg.model.Items.impl.heal.healHitPoint.items.MiddleHPBottle;
import com.web.rpg.model.Items.impl.heal.healHitPoint.items.SmallHPBottle;
import com.web.rpg.model.Items.impl.heal.healManaPoint.items.BigManaPointBottle;
import com.web.rpg.model.Items.impl.heal.healManaPoint.items.MiddleManaPointBottle;
import com.web.rpg.model.Items.impl.heal.healManaPoint.items.SmallManaPointBottle;
import com.web.rpg.model.Monsters.Monster;
import com.web.rpg.model.cities.City;
import com.web.rpg.service.character.CharacterService;
import com.web.rpg.service.cities.CityService;
import com.web.rpg.service.items.ItemService;
import com.web.rpg.service.monster.MonsterService;
import com.web.rpg.service.world.Event;
import com.web.rpg.service.world.EventDetails;
import com.web.rpg.service.world.WorldService;
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
public class WorldServiceImpl implements WorldService {

    private final CharacterService characterService;
    private final BigHPBottle bigHPBottle;
    private final MiddleHPBottle middleHPBottle;
    private final SmallHPBottle smallHPBottle;
    private final BigManaPointBottle bigManaPointBottle;
    private final MiddleManaPointBottle middleManaPointBottle;
    private final SmallManaPointBottle smallManaPointBottle;
    private final MonsterService monsterService;
    private final ItemService itemService;
    private final ItemGenerator itemGenerator;
    private final CityService cityService;

    @Autowired
    public WorldServiceImpl(CharacterDao characterDao,
                            CharacterService characterService,
                            BigHPBottle bigHPBottle,
                            MiddleHPBottle middleHPBottle,
                            SmallHPBottle smallHPBottle,
                            BigManaPointBottle bigManaPointBottle,
                            MiddleManaPointBottle middleManaPointBottle,
                            SmallManaPointBottle smallManaPointBottle,
                            MonsterService monsterService,
                            ItemService itemService,
                            ItemGenerator itemGenerator, CityService cityService) {
        this.characterService = characterService;
        this.bigHPBottle = bigHPBottle;
        this.middleHPBottle = middleHPBottle;
        this.smallHPBottle = smallHPBottle;
        this.bigManaPointBottle = bigManaPointBottle;
        this.middleManaPointBottle = middleManaPointBottle;
        this.smallManaPointBottle = smallManaPointBottle;
        this.monsterService = monsterService;
        this.itemService = itemService;
        this.itemGenerator = itemGenerator;
        this.cityService = cityService;
    }

    private static final Random RANDOM = new Random();
    private static final List<Event> EVENTS = Arrays.asList(Event.values());
    private static final List<EventDetails> EVENT_DETAILS = Arrays.asList(EventDetails.values());

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
        characterService.save(character);
    }

    private void fight(PlayerCharacter character) {
        Monster monster = monsterService.prepearMonsterForBattle(character);
        character.setMonster(monster);
        monsterService.updateOrCreate(monster);
        character.setCountToEndOfAction(1);
        characterService.save(character);
    }

    private void processEvent(PlayerCharacter character) {
        character.setCurrentAction(EVENT_DETAILS.get(RANDOM.nextInt(EVENT_DETAILS.size())).toString());
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
        characterService.save(character);
    }

    private void processFight(PlayerCharacter character) {
        if (CharacterClass.WIZZARD.equals(character.getCharacterClass())) {
            if (character.getManaPoints() > 0)
                character.setManaPoints(character.getManaPoints() - 50);
            else {
                if (healManaPoints(character)) {
                    character.setManaPoints(character.getManaPoints() - 50);
                } else {
                    character.setCountToEndOfAction(0);
                    character.setMonster(null);
                    return;
                }
            }
        }

        if (character.getHitPoints() < character.getMaxHitPoints() / 2) {
            if (!healHitPoints(character)) {
                character.setCountToEndOfAction(0);
                character.setMonster(null);
                return;
            }
        }

        Monster monster = (Monster) character.getMonster();
        monster.setHitPoint((monster.getHitPoint() - (character.getBaseDamage())));
        character.setHitPoints((character.getHitPoints() + character.getDefence() - monster.getDamage()));
        if (monster.getHitPoint() <= 0) {
            character.setCountToEndOfAction(0);
            IntStream.range(0, 100).mapToObj(i -> character).forEach(this::findRandomGoods);
            autoEquip(character, itemGenerator.generateItem(character));
            character.setMonster(null);
            monsterService.remove(monster);
        } else {
            monsterService.updateOrCreate(monster);
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

    private boolean healHitPoints(PlayerCharacter character) {
        if (character.getCountOfBigHitPointBottle() > 0){
            bigHPBottle.use(character);
            return true;
        }
        else if (character.getCountOfMiddleHitPointBottle() > 0) {
            middleHPBottle.use(character);
            return true;
        }
        else if (character.getCountOfSmallHitPointBottle() > 0) {
            smallHPBottle.use(character);
            return true;
        }
        else return false;
    }

    private boolean healManaPoints(PlayerCharacter character) {
        if (character.getCountOfBigManaPointBottles() > 0){
            bigManaPointBottle.use(character);
            return true;
        }
        else if (character.getCountOfMiddleManaPointBottles() > 0) {
            middleManaPointBottle.use(character);
            return true;
        }
        else if (character.getCountOfSmallManaPointBottles() > 0) {
            smallManaPointBottle.use(character);
            return true;
        }
        else return false;
    }
}