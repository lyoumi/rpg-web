package com.web.rpg.model.abilities.buffs;

import com.web.rpg.model.abilities.Magic;
import com.web.rpg.model.abilities.MagicClasses;

import java.util.Map;

/**
 * Created by pikachu on 18.07.17.
 */
public interface BuffMagic extends Magic{

    /**
     * Map, которая возвращает название эффекта с его параметром
     *
     * @return
     *          map
     */
    Map<BuffClasses, Integer> getEffect();

    /**
     *
     * @return
     *          int level
     */
    @Override
    int getLevel();

    void setLevel();

    /**
     * Метод, который возвращает ману, требуемую на выполнение заклинания
     *
     * @return
     *          int manaCost
     */
    @Override
    int getManaCost();

    @Override
    default void setDamage() {

    }

    int getTimeOfAction();

    /**
     * Метод, который возвращает тип заклинания из {@link MagicClasses}
     *
     * @return
     *          magicClass
     */
    @Override
    MagicClasses getMagicClass();
}
