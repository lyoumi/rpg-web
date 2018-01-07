package com.web.rpg.model.Quests;

import com.web.rpg.model.Characters.Character;

public interface QuestFactory {
    Quest createNewQuest(Character character);
}
