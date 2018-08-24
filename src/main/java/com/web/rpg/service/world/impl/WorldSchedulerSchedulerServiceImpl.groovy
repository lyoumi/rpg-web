package com.web.rpg.service.world.impl

import com.web.rpg.events.EventProcessorService
import com.web.rpg.model.Characters.CharacterClass
import com.web.rpg.model.Characters.PlayerCharacter
import com.web.rpg.model.Items.impl.Item
import com.web.rpg.model.Monsters.Monster
import com.web.rpg.model.cities.City
import com.web.rpg.service.character.CharacterService
import com.web.rpg.service.cities.CityService
import com.web.rpg.service.items.ItemService
import com.web.rpg.service.monster.MonsterService
import com.web.rpg.service.shared.util.HealingCharacterUtil
import com.web.rpg.service.world.Event
import com.web.rpg.service.world.WorldSchedulerService
import com.web.rpg.service.world.util.ItemGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WorldSchedulerSchedulerServiceImpl implements WorldSchedulerService {

    private final CharacterService characterService
    private final HealingCharacterUtil healingCharacterUtil
    private final MonsterService monsterService
    private final ItemService itemService
    private final ItemGenerator itemGenerator
    private final CityService cityService
    private final EventProcessorService eventProcessorService

    @Autowired
    WorldSchedulerSchedulerServiceImpl(CharacterService characterService,
                                       HealingCharacterUtil healingCharacterUtil,
                                       MonsterService monsterService,
                                       ItemService itemService,
                                       ItemGenerator itemGenerator,
                                       CityService cityService,
                                       EventProcessorService eventProcessorService) {
        this.characterService = characterService
        this.healingCharacterUtil = healingCharacterUtil
        this.monsterService = monsterService
        this.itemService = itemService
        this.itemGenerator = itemGenerator
        this.cityService = cityService
        this.eventProcessorService = eventProcessorService
    }

    private static final Random RANDOM = new Random()
    private static final List<Event> EVENTS = Arrays.asList(Event.values())

    @Override
    void changeWorldStatement() {
        changeCharactersStatements()
    }

    @Override
    void changeCharactersStatements() {
        def characters = characterService.findAll()
        if (characters != null && !characters.empty) {
            characters.each { character ->
                action(character)
            }
        }
    }

    private action = { PlayerCharacter character ->
        if (character.getCountToEndOfAction() == null || character.getCountToEndOfAction() < 1) {
            def event = EVENTS.get(RANDOM.nextInt(EVENTS.size()))
            switch (event) {
                case Event.FIGHT:
                    fight(character)
                    break
                case Event.SLEEP:
                    sleep(character)
                    break
                case Event.STORY:
                    processEvent(character)
                    break
            }
        } else {
            continueAction(character)
        }
    }

    private continueAction = { PlayerCharacter character ->
        processFight(character)
    }

    private sleep = { PlayerCharacter character ->
        character.setHitPoints(character.getMaxHitPoints())
        character.setManaPoints(character.getMaxManaPoints())
        String currentAction = "I am sleep, wait"
        character.currentAction = currentAction
        characterService.save(character)
    }

    private fight = { PlayerCharacter character ->
        Monster monster = monsterService.prepearMonsterForBattle(character)
        character.monster = monster
        monsterService.updateOrCreate(monster)
        String currentAction = "Fighting with " + monster.getName() +
                ", " +
                (int) monster.hitPoint + "hp"
        character.currentAction = currentAction
        character.countToEndOfAction = 1
        characterService.save(character)
    }

    private processEvent = { PlayerCharacter character ->
        travel(character)
        progressQuest(character)
        characterService.save(character)
    }

    private progressQuest = { PlayerCharacter character ->
        if (character.quest == null || character.story == null) {
            eventProcessorService.generateQuest(character)
        } else {
            if (character.story.end) {
                getRewards(character)
                character.quest = null
            } else {
                eventProcessorService.changeEvent(character)
            }
        }

    }

    private getRewards = { PlayerCharacter character ->
        character.experience = character.experience + character.quest.exp
        character.gold = character.gold + character.quest.gold
    }

    private travel = { PlayerCharacter character ->
        if (character.targetCity == null && character.currentCity != null) {
            def cities = new ArrayList(character.currentCity.citiesNear.keySet())
            def targetCity = cities[RANDOM.nextInt(cities.size())]
            character.targetCity = targetCity as City
            character.stepsToCity = character.currentCity.citiesNear[targetCity] as Integer
            character.currentCity = null
        } else if (character.targetCity == null && character.currentCity == null) {
            def cities = cityService.findAll()
            def targetCity = cities[RANDOM.nextInt(cities.size())]
            character.targetCity = targetCity as City
            character.stepsToCity = 42
        } else {
            character.stepsToCity = character.stepsToCity - 1
            if (character.stepsToCity < 1) {
                character.currentCity = character.targetCity
                character.targetCity = null
            }
        }
        findRandomGoods(character)
    }

    private processFight = { PlayerCharacter character ->
        if (CharacterClass.WIZZARD == character.characterClass) {
            if (character.manaPoints > 0)
                character.manaPoints = character.manaPoints - 50
            else {
                if (autoHealManaPoints(character)) {
                    character.manaPoints = character.manaPoints - 50
                } else {
                    character.countToEndOfAction = 0
                    character.monster = null
                    return
                }
            }
        }

        if (character.hitPoints < character.maxHitPoints / 2) {
            if (!autoHealHitPoints(character)) {
                character.countToEndOfAction = 0
                character.monster = null
                return
            }
        }

        def monster = character.monster
        monster.hitPoint = monster.hitPoint - character.baseDamage
        character.hitPoints = character.hitPoints + character.defence - monster.damage
        if (monster.hitPoint <= 0) {
            collectRewards(character, monster)
            monsterService.remove(monster)
        } else {
            monsterService.updateOrCreate(monster)
            character.currentAction = "Fighting with " + monster.name + ": " + monster.hitPoint + "hp"
        }
        characterService.save(character)
    }

    private collectRewards = { PlayerCharacter character, Monster monster ->
        character.countToEndOfAction = -1
        (0..100).each { findRandomGoods(character) }
        autoEquip(character, itemGenerator.generateItem())
        character.experience = character.experience + monster.experience
        character.expToNextLevel = character.expToNextLevel - monster.experience
        checkNewLevel(character)
        character.monster = null
        character.currentAction = "Killed a " + monster.name
    }

    private checkNewLevel = { PlayerCharacter character ->
        if (character.expToNextLevel < 1) {
            character.expToNextLevel = (int) (character.experience / 4)
            character.level = character.level + 1
            character.magicPoint = character.magicPoint + 1
        }
    }

    private autoEquip = { PlayerCharacter character, Item newItem ->
        def equipment = new HashMap<>()
        character.items.each { Item item ->
            equipment.put(item.getSlot(), item)
        }
        if (equipment.isEmpty() || !equipment.containsKey(newItem.slot)) {
            equipment[newItem.slot] = newItem
            character.items = new ArrayList<>(equipment.values())
            characterService.save(character)
        } else {
            if (newItem.activePoints > equipment.get(newItem.slot).getActivePoints()) {
                itemService.removeItem(equipment[newItem.slot])
                itemService.updateOrCreate(newItem)
                equipment[newItem.getSlot()] = newItem
                character.items = equipment.values() as List<Item>
                characterService.save(character)
            }
        }
    }

    private findRandomGoods = { PlayerCharacter character ->
        def chance = RANDOM.nextInt(99)
        if (chance == 42) {
            character.gold = character.gold + RANDOM.nextInt(10)
        } else if (chance == 47) {
            character.countOfBigManaPointBottles = character.countOfBigManaPointBottles + 1
        } else if (chance == 52) {
            character.countOfMiddleManaPointBottles = character.countOfMiddleManaPointBottles + 1
        } else if (chance == 29) {
            character.countOfSmallManaPointBottles = character.countOfSmallManaPointBottles + 1
        } else if (chance == 54) {
            character.countOfBigHitPointBottle = character.countOfBigHitPointBottle + 1
        } else if (chance == 21) {
            character.countOfMiddleHitPointBottle = character.countOfMiddleHitPointBottle + 1
        } else if (chance == 13) {
            character.countOfSmallHitPointBottle = character.countOfSmallHitPointBottle + 1
        }
    }

    private autoHealHitPoints = { PlayerCharacter character ->
        return healingCharacterUtil.autoHealHitPoints(character)
    }

    private autoHealManaPoints = { PlayerCharacter character ->
        return healingCharacterUtil.autoHealManaPoints(character)
    }
}