package com.web.rpg.service.monster;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Monsters.Monster;

public interface MonsterService {

    void applyDamage(Monster monster, double damage);

    Monster prepearMonsterForBattle(PlayerCharacter character);

    void remove(Monster monster);

    Monster updateOrCreate(Monster monster);
}
