package com.web.rpg.model.abilities.buffs.buffsclasses;

import com.web.rpg.model.abilities.MagicClasses;
import com.web.rpg.model.abilities.buffs.BuffClasses;
import com.web.rpg.model.abilities.buffs.BuffMagic;

import java.util.HashMap;
import java.util.Map;

public class DragonForm implements BuffMagic {

    private int level;
    private int timeOfActions = 1;
    private int manaCost;
    private int power = 10;
    private int defence = 50;

    private DragonForm(int level) {
        this.level = level;
        this.manaCost = getLevel()*7;
    }

    @Override
    public Map<BuffClasses, Integer> getEffect() {
        Map<BuffClasses, Integer> effects = new HashMap<>();
        effects.put(BuffClasses.power, power);
        effects.put(BuffClasses.defence, defence);
        return effects;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel() {
        timeOfActions++;
    }

    @Override
    public int getManaCost() {
        return manaCost;
    }

    @Override
    public void setDamage() {

    }

    @Override
    public int getTimeOfAction() {
        return timeOfActions;
    }

    @Override
    public MagicClasses getMagicClass() {
        return MagicClasses.BUFF;
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName();
    }
}
