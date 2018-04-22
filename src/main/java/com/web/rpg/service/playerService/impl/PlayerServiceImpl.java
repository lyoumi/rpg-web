package com.web.rpg.service.playerService.impl;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.repository.CharacterRepository;
import com.web.rpg.service.playerService.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public Integer getCountOfManaBottles(String id) {
        if (id == null) {
            return null;
        }
        PlayerCharacter character = characterRepository.findOne(id);
        return character.getCountOfBigManaPointBottles() +
                character.getCountOfMiddleManaPointBottles() +
                character.getCountOfSmallManaPointBottles();
    }

    @Override
    public Integer getCountOfHitPointBottles(String id) {
        if (id == null) {
            return null;
        }
        PlayerCharacter character = characterRepository.findOne(id);
        return character.getCountOfBigHitPointBottle() +
                character.getCountOfMiddleHitPointBottle() +
                character.getCountOfSmallHitPointBottle();
    }
}
