package com.web.rpg.service.player.impl;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.service.character.CharacterService;
import com.web.rpg.service.player.PlayerService;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final CharacterService characterService;

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
