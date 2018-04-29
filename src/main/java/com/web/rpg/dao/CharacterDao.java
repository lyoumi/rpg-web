package com.web.rpg.dao;

import com.web.rpg.entity.PlayerCharacterEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CharacterDao extends CrudRepository<PlayerCharacterEntity, UUID> {
    PlayerCharacterEntity getCharacterByPlayerId(UUID playerId);
}
