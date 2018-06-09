package com.web.rpg.model.abilities.buffs.buffsclasses;

import com.web.rpg.model.abilities.MagicClasses;
import com.web.rpg.model.abilities.buffs.BuffClasses;
import com.web.rpg.model.abilities.buffs.BuffMagic;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pikachu on 18.07.17.
 */
public class ArchersBuff implements BuffMagic {

    private int level;
    private int timeOfActions = 1;
    private final int agility = 6;
    private final int power = 5;
    private final int intelligence = 4;

    public ArchersBuff(int level){
        this.level = level;
    }

    @Override
    public Map<BuffClasses, Integer> getEffect() {
        Map<BuffClasses, Integer> effects = new HashMap<>();
        effects.put(BuffClasses.agility, agility);
        effects.put(BuffClasses.power, power);
        effects.put(BuffClasses.intelligence, intelligence);
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
        return 0;
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
