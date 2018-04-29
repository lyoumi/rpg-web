package com.web.rpg.service.player.impl;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.dao.CharacterDao;
import com.web.rpg.service.character.CharacterService;
import com.web.rpg.service.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private CharacterDao characterDao;
    @Autowired
    private CharacterService characterService;

    @Override
    public Integer getCountOfManaBottles(UUID id) {
        if (id == null) {
            return null;
        }
        PlayerCharacter character = characterService.findById(id);
        return character.getCountOfBigManaPointBottles() +
                character.getCountOfMiddleManaPointBottles() +
                character.getCountOfSmallManaPointBottles();
    }

    @Override
    public Integer getCountOfHitPointBottles(UUID id) {
        if (id == null) {
            return null;
        }
        PlayerCharacter character = characterService.findById(id);
        return character.getCountOfBigHitPointBottle() +
                character.getCountOfMiddleHitPointBottle() +
                character.getCountOfSmallHitPointBottle();
    }
}
