package com.web.rpg.model.abilities.debuffs.debuffsclasses.damage;

import com.web.rpg.model.abilities.MagicClasses;
import com.web.rpg.model.abilities.debuffs.DebuffMagic;

public class BurningJoe implements DebuffMagic {

    private int level;
    private int damage;
    private int timeOfAction = 5;

    private BurningJoe(int level){
        this.level = level + 1;
        this.damage = getLevel()*3;
    }

    @Override
    public int getTimeOfAction() {
        timeOfAction -= 1;
        return timeOfAction;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getManaCost() {
        return 30;
    }

    @Override
    public void setDamage() {

    }

    @Override
    public int getDamage() {
        return damage;
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
