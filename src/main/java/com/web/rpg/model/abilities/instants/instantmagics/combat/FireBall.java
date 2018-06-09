package com.web.rpg.model.abilities.instants.instantmagics.combat;

import com.web.rpg.model.abilities.MagicClasses;
import com.web.rpg.model.abilities.instants.InstantMagic;

/**
 * Боевая магия, наносящая урон противнику
 */
public class FireBall implements InstantMagic {
    private int level;
    private int damage = 50;
    private int manaCost;

    public FireBall(int level){
        this.level = level;
        this.manaCost = getLevel() * 10;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void setDamage() {
        this.damage += 50;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getManaCost() {
        return manaCost;
    }


    @Override
    public MagicClasses getMagicClass() {
        return MagicClasses.COMBAT;
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName();
    }
}
