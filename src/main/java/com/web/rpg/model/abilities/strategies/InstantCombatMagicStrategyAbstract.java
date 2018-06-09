package com.web.rpg.model.abilities.strategies;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.abilities.MagicClasses;
import com.web.rpg.model.abilities.instants.InstantMagic;
import com.web.rpg.service.monster.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InstantCombatMagicStrategyAbstract extends AbstractDefaultMagicStrategy {
    private final MonsterService monsterService;

    @Autowired
    public InstantCombatMagicStrategyAbstract(MonsterService monsterService) {
        this.monsterService = monsterService;
    }


    @Override
    public void useMagic(PlayerCharacter character) {
        InstantMagic instantMagic = (InstantMagic) character.getMagic();
        monsterService.applyDamage(character.getMonster(), instantMagic.getDamage());
        character.setCurrentAction("Fighting with " + character.getMonster().getName() + ": " + character.getMonster().getHitPoint() + "hp");
        calculateMana(character);
    }

    @Override
    public MagicClasses getMagicClass() {
        return MagicClasses.COMBAT;
    }
}
