package com.web.rpg.model.Monsters;

import com.web.rpg.model.Characters.PlayerCharacter;

public interface MonsterFactory {
    Monster createNewMonster(PlayerCharacter character);
}
