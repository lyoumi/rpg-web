package com.web.rpg.service.worldServices;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Characters.characterclasses.CharacterClass;
import com.web.rpg.model.Items.itemsclasses.healclasses.healHitPoint.items.BigHPBottle;
import com.web.rpg.model.Items.itemsclasses.healclasses.healHitPoint.items.MiddleHPBottle;
import com.web.rpg.model.Items.itemsclasses.healclasses.healHitPoint.items.SmallHPBottle;
import com.web.rpg.model.Items.itemsclasses.healclasses.healManaPoint.items.BigManaPointBottle;
import com.web.rpg.model.Items.itemsclasses.healclasses.healManaPoint.items.MiddleManaPointBottle;
import com.web.rpg.model.Items.itemsclasses.healclasses.healManaPoint.items.SmallManaPointBottle;
import com.web.rpg.model.Monsters.Monster;
import com.web.rpg.model.Monsters.monstersclasses.MediumBot;
import com.web.rpg.model.Monsters.service.MosterService;
import com.web.rpg.repository.CharacterRepository;
import com.web.rpg.service.characterServices.interfaces.CharacterService;
import com.web.rpg.service.worldServices.interfaces.WorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Service
public class WorldServiceImpl implements WorldService {

    private CharacterRepository characterRepository;

    private CharacterService characterService;
    private BigHPBottle bigHPBottle;
    private MiddleHPBottle middleHPBottle;
    private SmallHPBottle smallHPBottle;
    private BigManaPointBottle bigManaPointBottle;
    private MiddleManaPointBottle middleManaPointBottle;
    private SmallManaPointBottle smallManaPointBottle;
    private MosterService monsterService;

    @Autowired
    public WorldServiceImpl(CharacterRepository characterRepository,
                            CharacterService characterService,
                            BigHPBottle bigHPBottle,
                            MiddleHPBottle middleHPBottle,
                            SmallHPBottle smallHPBottle,
                            BigManaPointBottle bigManaPointBottle,
                            MiddleManaPointBottle middleManaPointBottle,
                            SmallManaPointBottle smallManaPointBottle,
                            MosterService monsterService) {
        this.characterRepository = characterRepository;
        this.characterService = characterService;
        this.bigHPBottle = bigHPBottle;
        this.middleHPBottle = middleHPBottle;
        this.smallHPBottle = smallHPBottle;
        this.bigManaPointBottle = bigManaPointBottle;
        this.middleManaPointBottle = middleManaPointBottle;
        this.smallManaPointBottle = smallManaPointBottle;
        this.monsterService = monsterService;
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
        List<PlayerCharacter> characters = (List<PlayerCharacter>) characterRepository.findAll();
        characters.parallelStream().forEach(this::action);
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
        characterRepository.save(character);
    }

    private void fight(PlayerCharacter character) {
        Monster monster = monsterService.prepearMonsterForBattle(character);
        character.setMonster(monster);
        character.setCountToEndOfAction(1);
        characterRepository.save(character);
    }

    private void processEvent(PlayerCharacter character) {
        character.setCurrentAction(EVENT_DETAILS.get(RANDOM.nextInt(EVENT_DETAILS.size())).toString());
        findRandomGoods(character);
        characterRepository.save(character);
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

        MediumBot monster = (MediumBot) character.getMonster();
        monster.setHitPoint((monster.getHitPoint() - (character.getBaseDamage())));
        character.setHitPoints((character.getHitPoints() + character.getDefence() - monster.getDamage()));
        if (monsterService.isDead(monster)) {
            character.setCountToEndOfAction(0);
            IntStream.range(0, 100).mapToObj(i -> character).forEach(this::findRandomGoods);
        }
        characterRepository.save(character);
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