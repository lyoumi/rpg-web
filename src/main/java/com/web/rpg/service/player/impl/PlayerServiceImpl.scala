package com.web.rpg.service.player.impl

import java.util.UUID

import com.web.rpg.service.character.CharacterService
import com.web.rpg.service.player.PlayerService
import org.springframework.beans.factory.annotation.Autowired

class PlayerServiceImpl @Autowired()(val characterService: CharacterService) extends PlayerService {

  override def getCountOfManaBottles(id: UUID): Integer = {
    if (id == null) return null
    val character = characterService.findById(id)
    character.getCountOfBigManaPointBottles +
      character.getCountOfMiddleManaPointBottles +
      character.getCountOfSmallManaPointBottles
  }

  override def getCountOfHitPointBottles(id: UUID): Integer = {
    if (id == null) return null
    val character = characterService.findById(id)
    character.getCountOfBigHitPointBottle +
      character.getCountOfMiddleHitPointBottle +
      character.getCountOfSmallHitPointBottle
  }

}
