package com.web.rpg.repository;

import com.web.rpg.model.Characters.PlayerCharacter;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CharacterRepository extends CrudRepository<PlayerCharacter, UUID> {
    PlayerCharacter getCharacterByPlayerId(String playerId);
}
