package com.web.rpg.repository;

import com.web.rpg.entity.PlayerCharacterEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CharacterRepository extends CrudRepository<PlayerCharacterEntity, UUID> {
    PlayerCharacterEntity getCharacterByPlayerId(UUID playerId);
}
