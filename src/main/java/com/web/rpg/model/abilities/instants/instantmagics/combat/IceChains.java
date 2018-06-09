package com.web.rpg.model.abilities.instants.instantmagics.combat;

import com.web.rpg.model.abilities.MagicClasses;
import com.web.rpg.model.abilities.instants.InstantMagic;

/**
 * Боевая магия, наносящая урон противнику
 */
public class IceChains implements InstantMagic {

    private int damage = 30;
    private int level;
    private int manaCost;
    
    private IceChains(int level){
        this.level = level;
        this.manaCost = level * 3;
    }


    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void setDamage() {
        this.damage += 30;
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
