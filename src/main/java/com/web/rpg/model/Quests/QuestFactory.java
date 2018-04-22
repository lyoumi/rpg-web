package com.web.rpg.model.Quests;

import com.web.rpg.model.Characters.PlayerCharacter;

public interface QuestFactory {
    Quest createNewQuest(PlayerCharacter character);
}
