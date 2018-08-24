package com.web.rpg.service.player.impl

import com.web.rpg.dao.CharacterDao
import com.web.rpg.service.character.CharacterService
import com.web.rpg.service.player.PlayerService
import org.springframework.stereotype.Service

@Service
class PlayerServiceImpl implements PlayerService {

    private final characterDao;
    private final characterService

    PlayerServiceImpl(CharacterDao characterDao,
                      CharacterService characterService) {
        this.characterDao = characterDao
        this.characterService = characterService
    }

    @Override
    Integer getCountOfManaBottles(UUID id) {
        if (id == null) {
            return null
        }
        def character = characterService.findById(id)
        character.getCountOfBigManaPointBottles() +
                character.getCountOfMiddleManaPointBottles() +
                character.getCountOfSmallManaPointBottles()
    }

    @Override
    Integer getCountOfHitPointBottles(UUID id) {
        if (id == null) {
            return null
        }
        def character = characterService.findById(id)
        character.getCountOfBigHitPointBottle() +
                character.getCountOfMiddleHitPointBottle() +
                character.getCountOfSmallHitPointBottle()
    }
}
