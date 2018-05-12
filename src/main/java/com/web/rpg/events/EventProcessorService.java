package com.web.rpg.events;

import com.web.rpg.model.Characters.PlayerCharacter;

public interface EventProcessorService {
    void generateQuest(PlayerCharacter character);

    void changeEvent(PlayerCharacter character);
}
