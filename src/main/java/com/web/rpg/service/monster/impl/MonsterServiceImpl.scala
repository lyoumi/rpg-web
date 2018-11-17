package com.web.rpg.service.monster.impl

import java.io.IOException
import java.util
import java.util.{Properties, Random, UUID}

import scala.collection.mutable.ArrayBuffer
import com.web.rpg.model.Characters.{CharacterClass, PlayerCharacter}
import com.web.rpg.model.Monsters.Monster
import com.web.rpg.repository.MonsterRepository
import com.web.rpg.service.monster.MonsterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MonsterServiceImpl @Autowired()(val monsterRepository: MonsterRepository) extends MonsterService {

  private val RANDOM: Random = new Random

  override def applyDamage(monster: Monster, damage: Double): Unit = {
    val currentHitPoint: Double = monster.getHitPoint - damage
    if (currentHitPoint >= 0) monster.setHitPoint(currentHitPoint)
    else monster.setHitPoint(0)
  }

  override def prepearMonsterForBattle(character: PlayerCharacter): Monster = {
    val monster: Monster = new Monster
    monster.setMonsterId(UUID.randomUUID)
    if (CharacterClass.BERSERK == character.getCharacterClass) monster.setLevel(character.getLevel + 4)
    else monster.setLevel(character.getLevel + 1)
    if (character.getLevel > 5) {
      monster.setExperience(character.getLevel * 50)
      monster.setHitPoint(monster.getLevel * 150)
      monster.setDamage(monster.getLevel * 40)
    }
    else {
      monster.setExperience(character.getLevel * 10)
      monster.setHitPoint(monster.getLevel * 70)
      monster.setDamage(monster.getLevel * 20)
    }
    monster.setName(generateName)
    monster
  }

  override def remove(monster: Monster): Unit = {
    monsterRepository.delete(monster)
  }

  override def updateOrCreate(monster: Monster): Monster = {
    monsterRepository.save(monster)
    monsterRepository.findOne(monster.asInstanceOf[Monster].getMonsterId)
  }

  private def generateName: String = {
    val prop: Properties = new Properties
    try
      prop.load(getClass.getClassLoader.getResourceAsStream("properties/monster-names.properties"))
    catch {
      case e: IOException =>
        e.printStackTrace()
    }

    val characterNames: ArrayBuffer[String] = ArrayBuffer()
    prop.entrySet()
      .forEach((entry: util.Map.Entry[AnyRef, AnyRef]) =>
        characterNames ++ entry.getValue.toString)

    characterNames(RANDOM.nextInt(characterNames.length))
  }
}
