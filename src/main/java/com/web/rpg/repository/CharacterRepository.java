package com.web.rpg.repository;

import com.web.rpg.model.Characters.PlayerCharacter;
import org.springframework.data.repository.CrudRepository;

public interface CharacterRepository extends CrudRepository<PlayerCharacter, String> {
    PlayerCharacter getCharacterByPlayerId(String playerId);
}
