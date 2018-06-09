package com.web.rpg.model.abilities.strategies;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.abilities.MagicClasses;
import com.web.rpg.model.abilities.instants.InstantMagic;
import org.springframework.stereotype.Component;

@Component
public class InstantHealingMagicStrategy extends AbstractDefaultMagicStrategy {
    @Override
    public void useMagic(PlayerCharacter character) {
        InstantMagic instantMagic = (InstantMagic) character.getMagic();
        double currentHitPoint = character.getHitPoints() + instantMagic.getDamage();
        if (currentHitPoint <= character.getMaxHitPoints()) {
            character.setHitPoints(currentHitPoint);
        } else {
            character.setHitPoints(character.getMaxHitPoints());
        }
        calculateMana(character);
    }

    @Override
    public MagicClasses getMagicClass() {
        return MagicClasses.HEALING;
    }
}
