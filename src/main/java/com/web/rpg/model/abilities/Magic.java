package com.web.rpg.model.abilities;

/**
 * Created by pikachu on 18.07.17.
 */

/**
 * Базовый интерфейс для магий.
 */
public interface Magic {

    /**
     *
     * @return
     *          int level
     */
    int getLevel();

    /**
     * Метод, который возвращает ману, требуемую на выполнение заклинания
     *
     * @return
     *          int manaCost
     */
    int getManaCost();

    void setDamage();

    /**
     * Метод, который возвращает тип заклинания из {@link MagicClasses}
     *
     * @return
     *          magicClass
     */
    MagicClasses getMagicClass();

}
