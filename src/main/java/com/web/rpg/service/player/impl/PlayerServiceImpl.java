package com.web.rpg.service.player.impl;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.repository.CharacterRepository;
import com.web.rpg.service.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public Integer getCountOfManaBottles(UUID id) {
        if (id == null) {
            return null;
        }
        PlayerCharacter character = characterRepository.findOne(id);
        return character.getCountOfBigManaPointBottles() +
                character.getCountOfMiddleManaPointBottles() +
                character.getCountOfSmallManaPointBottles();
    }

    @Override
    public Integer getCountOfHitPointBottles(UUID id) {
        if (id == null) {
            return null;
        }
        PlayerCharacter character = characterRepository.findOne(id);
        return character.getCountOfBigHitPointBottle() +
                character.getCountOfMiddleHitPointBottle() +
                character.getCountOfSmallHitPointBottle();
    }
}
