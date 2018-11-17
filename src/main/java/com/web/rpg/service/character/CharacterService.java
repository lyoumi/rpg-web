package com.web.rpg.service.character;

import com.web.rpg.model.Characters.PlayerCharacter;

import java.util.List;
import java.util.UUID;


public interface CharacterService {

    void processCharacters();

    Integer getCountOfManaBottles(UUID id);

    Integer getCountOfHitPointBottles(UUID id);

    PlayerCharacter createOrUpdateCharacter(PlayerCharacter character);

    List<PlayerCharacter> findAll();

    PlayerCharacter save(PlayerCharacter character);

    PlayerCharacter findById(UUID id);

    PlayerCharacter findByPlayerId(UUID id);

    void deleteAll();

    void healCharacterHitPoints(PlayerCharacter character);

    UUID prepeareCharacter();

    void healCharacterManaPoints(PlayerCharacter character);
}
