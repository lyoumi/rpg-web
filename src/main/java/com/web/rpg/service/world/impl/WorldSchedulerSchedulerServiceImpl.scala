package com.web.rpg.service.world.impl

import java.util
import java.util.Random
import java.util.stream.IntStream

import com.web.rpg.events.EventProcessorService
import com.web.rpg.model.Characters.{CharacterClass, PlayerCharacter}
import com.web.rpg.model.Items.EquipmentSlot
import com.web.rpg.model.Items.impl.Item
import com.web.rpg.model.Monsters.Monster
import com.web.rpg.model.cities.City
import com.web.rpg.service.character.CharacterService
import com.web.rpg.service.cities.CityService
import com.web.rpg.service.items.ItemService
import com.web.rpg.service.monster.MonsterService
import com.web.rpg.service.shared.util.HealingCharacterUtil
import com.web.rpg.service.world.util.Event.{FIGHT, SLEEP, STORY}
import com.web.rpg.service.world.util.ItemGenerator
import com.web.rpg.service.world.WorldSchedulerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import scala.collection.JavaConverters._

@Service
class WorldSchedulerSchedulerServiceImpl @Autowired()(
                                                       characterService: CharacterService,
                                                       healingCharacterUtil: HealingCharacterUtil,
                                                       monsterService: MonsterService,
                                                       itemService: ItemService,
                                                       itemGenerator: ItemGenerator,
                                                       cityService: CityService,
                                                       eventProcessorService: EventProcessorService
                                                     ) extends WorldSchedulerService {

  import com.web.rpg.service.world.util.Event

  private val RANDOM = new Random
  private val EVENTS: List[Event] = Event.values.toList

  private val FIGHT_ACTION = "Fighting with %s: %shp"
  private val KILL_ACTION = "Killed a %s"
  private val SLEEP_ACTION = "I am sleep, wait"

  override def changeWorldStatement(): Unit = {
    changeCharactersStatements()
  }

  override def changeCharactersStatements(): Unit = {
    val characters = characterService.findAll
    if (characters != null && !characters.isEmpty) {
      characters.forEach(action)
    }
  }

  private def action(character: PlayerCharacter): PlayerCharacter = {
    if (character.getCountToEndOfAction == null || character.getCountToEndOfAction < 1) {
      val event: Event = EVENTS(RANDOM.nextInt(EVENTS.size))
      event match {
        case STORY =>
          processEvent(character)
        case FIGHT =>
          fight(character)
        case SLEEP =>
          sleep(character)

      }
    } else {
      continueAction(character)
    }
  }

  private def continueAction(character: PlayerCharacter): PlayerCharacter = {
    processFight(character)
  }

  private def sleep(character: PlayerCharacter): PlayerCharacter = {
    character.setHitPoints(character.getMaxHitPoints)
    character.setManaPoints(character.getMaxManaPoints)
    val currentAction = SLEEP_ACTION
    character.setCurrentAction(currentAction)
    characterService.save(character)
  }

  private def fight(character: PlayerCharacter): PlayerCharacter = {
    val monster = monsterService.prepareMonsterForBattle(character)
    character.setMonster(monster)
    monsterService.updateOrCreate(monster)
    val currentAction = FIGHT_ACTION.format(monster.getName, monster.getHitPoint.toInt)
    character.setCurrentAction(currentAction)
    character.setCountToEndOfAction(1)
    characterService.save(character)
  }

  private def processEvent(character: PlayerCharacter): PlayerCharacter = {
    travel(character)
    progressQuest(character)
    characterService.save(character)
  }

  private def progressQuest(character: PlayerCharacter): Unit = {
    if (character.getQuest == null || character.getStory == null) eventProcessorService.generateQuest(character)
    else if (character.getStory.isEnd) {
      getRewards(character)
      character.setQuest(null)
    }
    else eventProcessorService.changeEvent(character)
  }

  private def getRewards(character: PlayerCharacter): Unit = {
    character.setExperience(character.getExperience + character.getQuest.getExp)
    character.setGold(character.getGold + character.getQuest.getGold)
  }

  private def travel(character: PlayerCharacter): Unit = {
    if (character.getTargetCity == null && character.getCurrentCity != null) {
      val cities: List[City] = character.getCurrentCity.getCitiesNear.keySet.asScala.toList
      val targetCity: City = cities(RANDOM.nextInt(cities.size))
      character.setTargetCity(targetCity)
      character.setStepsToCity(character.getCurrentCity.getCitiesNear.get(targetCity))
      character.setCurrentCity(null)
    } else if (character.getTargetCity == null && character.getCurrentCity == null) {
      val cities = cityService.findAll
      val targetCity = cities.get(RANDOM.nextInt(cities.size))
      character.setTargetCity(targetCity)
      character.setStepsToCity(42)
    }
    else {
      character.setStepsToCity(character.getStepsToCity - 1)
      if (character.getStepsToCity < 1) {
        character.setCurrentCity(character.getTargetCity)
        character.setTargetCity(null)
      }
    }
    findRandomGoods(character)
  }

  private def processFight(character: PlayerCharacter): PlayerCharacter = {
    if (CharacterClass.WIZZARD == character.getCharacterClass) if (character.getManaPoints > 0) {
      character.setManaPoints(character.getManaPoints - 50)
    } else if (autoHealManaPoints(character)) {
      character.setManaPoints(character.getManaPoints - 50)
    } else {
      character.setCountToEndOfAction(0)
      character.setMonster(null)
      None
    }
    if (character.getHitPoints < character.getMaxHitPoints / 2) if (!autoHealHitPoints(character)) {
      character.setCountToEndOfAction(0)
      character.setMonster(null)
      None
    }
    val monster = character.getMonster
    monster.setHitPoint(monster.getHitPoint - character.getBaseDamage)
    character.setHitPoints(character.getHitPoints + character.getDefence - monster.getDamage)
    if (monster.getHitPoint <= 0) {
      collectRewards(character, monster)
      monsterService.remove(monster)
    } else {
      monsterService.updateOrCreate(monster)
      character.setCurrentAction(FIGHT_ACTION.format(monster.getName, monster.getHitPoint))
    }
    characterService.save(character)
  }

  private def collectRewards(character: PlayerCharacter, monster: Monster): Unit = {
    character.setCountToEndOfAction(-1)
    IntStream.range(0, 100).mapToObj((i: Int) => character).forEach(this.findRandomGoods)
    autoEquip(character, itemGenerator.generateItem(character))
    character.setExperience(character.getExperience + monster.getExperience)
    character.setExpToNextLevel(character.getExpToNextLevel - monster.getExperience)
    checkNewLevel(character)
    character.setMonster(null)
    character.setCurrentAction(KILL_ACTION.format(monster.getName))
  }

  private def checkNewLevel(character: PlayerCharacter): Unit = {
    if (character.getExpToNextLevel < 1) {
      character.setExpToNextLevel((character.getExperience / 4).toInt)
      character.setLevel(character.getLevel + 1)
      character.setMagicPoint(character.getMagicPoint + 1)
    }
  }

  private def autoEquip(character: PlayerCharacter, newItem: Item): Unit = {
    val equipment: Map[EquipmentSlot, Item] = Map[EquipmentSlot, Item]()
    character.getItems.forEach(item => {
      equipment + (item.getSlot -> item)
    })

    if (equipment.isEmpty || !equipment.contains(newItem.getSlot)) {
      equipment + (newItem.getSlot -> newItem)
      character.setItems(equipment.values.toList.asJava)
      characterService.save(character)
    }
    else if (newItem.getActivePoints > equipment(newItem.getSlot).getActivePoints) {
      itemService.removeItem(equipment(newItem.getSlot))
      itemService.updateOrCreate(newItem)
      equipment + (newItem.getSlot -> newItem)
      character.setItems(equipment.values.asInstanceOf[util.List[Item]])
      characterService.save(character)
    }
  }

  private def findRandomGoods(character: PlayerCharacter): Unit = {
    val chance = RANDOM.nextInt(99)
    if (chance == 42) character.setGold(character.getGold + RANDOM.nextInt(10))
    else if (chance == 47) character.setCountOfBigManaPointBottles(character.getCountOfBigManaPointBottles + 1)
    else if (chance == 52) character.setCountOfMiddleManaPointBottles(character.getCountOfMiddleManaPointBottles + 1)
    else if (chance == 29) character.setCountOfSmallManaPointBottles(character.getCountOfSmallManaPointBottles + 1)
    else if (chance == 54) character.setCountOfBigHitPointBottle(character.getCountOfBigHitPointBottle + 1)
    else if (chance == 21) character.setCountOfMiddleHitPointBottle(character.getCountOfMiddleHitPointBottle + 1)
    else if (chance == 13) character.setCountOfSmallHitPointBottle(character.getCountOfSmallHitPointBottle + 1)
  }

  private def autoHealHitPoints(character: PlayerCharacter) = healingCharacterUtil.autoHealHitPoints(character)

  private def autoHealManaPoints(character: PlayerCharacter) = healingCharacterUtil.autoHealManaPoints(character)
}
