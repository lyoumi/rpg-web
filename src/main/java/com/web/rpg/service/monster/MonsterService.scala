package com.web.rpg.service.monster

import com.web.rpg.model.Characters.PlayerCharacter
import com.web.rpg.model.Monsters.Monster

trait MonsterService {

  def applyDamage(monster: Monster, damage: Double): Unit

  def prepareMonsterForBattle(character: PlayerCharacter): Monster

  def remove(monster: Monster): Unit

  def updateOrCreate(monster: Monster): Monster

}
