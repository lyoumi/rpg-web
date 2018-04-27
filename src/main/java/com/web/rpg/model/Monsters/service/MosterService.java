package com.web.rpg.model.Monsters.service;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Monsters.Monster;
import com.web.rpg.model.Monsters.monstersclasses.MediumBot;
import org.springframework.stereotype.Service;

@Service
public interface MosterService {

    Monster prepearMonsterForBattle(PlayerCharacter character);

    boolean isDead(Monster monster);

    void remove(MediumBot monster);

    Monster updateOrCreate(Monster monster);
}
