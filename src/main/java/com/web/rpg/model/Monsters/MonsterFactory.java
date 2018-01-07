package com.web.rpg.model.Monsters;

import com.web.rpg.model.Characters.Character;

public interface MonsterFactory {
    Monster createNewMonster(Character character);
}
