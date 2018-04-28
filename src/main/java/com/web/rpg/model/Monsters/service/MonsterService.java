package com.web.rpg.model.Monsters.service;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Monsters.monstersclasses.Monster;

public interface MonsterService {

    Monster prepearMonsterForBattle(PlayerCharacter character);

    void remove(Monster monster);

    Monster updateOrCreate(Monster monster);
}
