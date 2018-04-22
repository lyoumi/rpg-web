package com.web.rpg.model.Monsters.service;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Monsters.Monster;
import org.springframework.stereotype.Service;

@Service
public interface MosterService {

    Monster prepearMonsterForBattle(PlayerCharacter character);

    boolean isDead(Monster monster);
}
