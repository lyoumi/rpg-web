package com.web.rpg.service.characterServices.interfaces;

import org.springframework.stereotype.Service;

@Service
public interface CharacterService {

    void processCharacters();

    Integer getCountOfManaBootles(String id);

    Integer getCountOfHitPointBootles(String id);
}
