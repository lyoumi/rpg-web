package com.web.rpg.service.character;

import com.web.rpg.model.Characters.PlayerCharacter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface CharacterService {

    void processCharacters();

    Integer getCountOfManaBootles(UUID id);

    Integer getCountOfHitPointBootles(UUID id);

    PlayerCharacter createOrUpdateCharacter(PlayerCharacter character);

    List<PlayerCharacter> findAll();
}
