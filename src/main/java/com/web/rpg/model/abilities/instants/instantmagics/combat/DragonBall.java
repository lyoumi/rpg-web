package com.web.rpg.model.abilities.instants.instantmagics.combat;

import com.web.rpg.model.abilities.MagicClasses;
import com.web.rpg.model.abilities.instants.InstantMagic;

public class DragonBall implements InstantMagic{

    private int level;
    private int damage = 70;
    private int manaCost;

    private DragonBall(int level) {
        this.level = level;
        this.manaCost = getLevel() * 5;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void setDamage() {
        damage += 70;
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

    public String toString(){
        return this.getClass().getSimpleName();
    }
}
