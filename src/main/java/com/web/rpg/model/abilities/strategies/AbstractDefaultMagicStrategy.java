package com.web.rpg.model.abilities.strategies;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.abilities.MagicClasses;

public abstract class AbstractDefaultMagicStrategy {

    public abstract void useMagic(PlayerCharacter character);

    public abstract MagicClasses getMagicClass();

    protected void calculateMana(PlayerCharacter character) {
        double currentMP = character.getManaPoints();
        int manaCost = character.getMagic().getManaCost();
        if (currentMP >= manaCost) {
            character.setManaPoints(currentMP - manaCost);
        }
    }
}
