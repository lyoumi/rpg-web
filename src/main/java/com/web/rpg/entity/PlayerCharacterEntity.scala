package com.web.rpg.entity

import java.io.Serializable
import java.util.UUID

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class PlayerCharacterEntity ()(
  @Id val id: UUID,
  val playerId: UUID,

  val name: String,

  val agility: Integer,

  val intelligence: Integer,
  val power: Integer,

  val experience: Long,
  val level: Integer,

  val characterClass: String,

  val hitPoints: Double,
  val maxHitPoints: Double,
  val manaPoints: Double,
  val maxManaPoints: Double,

  val baseDamage: Double,
  val defence: Integer,

  val items: Array[Byte],
  val magic: Array[Byte],
  val magicPoint: Integer,

  val expToNextLevel: Integer,
  val gold: Integer,
  val buffMagic: Array[Byte],

  val additionPower: Integer,
  val additionIntelligence: Integer,
  val additionAgility: Integer,

  val countOfBigHitPointBottle: Integer,
  val countOfMiddleHitPointBottle: Integer,
  val countOfSmallHitPointBottle: Integer,

  val countOfBigManaPointBottles: Integer,
  val countOfMiddleManaPointBottles: Integer,
  val countOfSmallManaPointBottles: Integer,

  val quest: Array[Byte],

  val currentAction: String,
  val actionType: String,
  val countToEndOfAction: Integer,

  val monster: Array[Byte],
  val story: Array[Byte],
  val currentCity: Array[Byte],
  val targetCity: Array[Byte],
  val stepsToCity: Integer
) extends Serializable {
  def getId: UUID = id

  def getPlayerId: UUID = playerId

  def getName: String = name

  def getAgility: Integer = agility

  def getIntelligence: Integer = intelligence

  def getPower: Integer = power

  def getExperience: Long = experience

  def getLevel: Integer = level

  def getCharacterClass: String = characterClass

  def getHitPoints: Double = hitPoints

  def getMaxHitPoints: Double = maxHitPoints

  def getManaPoints: Double = manaPoints

  def getMaxManaPoints: Double = maxManaPoints

  def getBaseDamage: Double = baseDamage

  def getDefence: Integer = defence

  def getItems: Array[Byte] = items

  def getMagic: Array[Byte] = magic

  def getMagicPoint: Integer = magicPoint

  def getExpToNextLevel: Integer = expToNextLevel

  def getGold: Integer = gold

  def getBuffMagic: Array[Byte] = buffMagic

  def getAdditionPower: Integer = additionPower

  def getAdditionIntelligence: Integer = additionIntelligence

  def getAdditionAgility: Integer = additionAgility

  def getCountOfBigHitPointBottle: Integer = countOfBigHitPointBottle

  def getCountOfMiddleHitPointBottle: Integer = countOfMiddleHitPointBottle

  def getCountOfSmallHitPointBottle: Integer = countOfSmallHitPointBottle

  def getCountOfBigManaPointBottles: Integer = countOfBigManaPointBottles

  def getCountOfMiddleManaPointBottles: Integer = countOfMiddleManaPointBottles

  def getCountOfSmallManaPointBottles: Integer = countOfSmallManaPointBottles

  def getQuest: Array[Byte] = quest

  def getCurrentAction: String = currentAction

  def getActionType: String = actionType

  def getCountToEndOfAction: Integer = countToEndOfAction

  def getMonster: Array[Byte] = monster

  def getStory: Array[Byte] = story

  def getCurrentCity: Array[Byte] = currentCity

  def getTargetCity: Array[Byte] = targetCity

  def getStepsToCity: Integer = stepsToCity
}
