package com.web.rpg.model.Items.itemsclasses;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.itemsclasses.healclasses.HealingItemsList;

public interface HealingItems {

    /**
     * Метод, который возвращает стоимость предмета.
     *
     * @return
     *          price of current item.
     */
    int getPrice();

    /**
     * Метод, возвращающий тип этого предмета из {@link HealingItemsList}
     *
     * @return
     *          item type from {@link HealingItemsList}
     */
    HealingItemsList getHealingItemClass();

    /**
     * Метод, реализующий использование  текущего предмета.
     *
     * @param character
     *              character implementation of {@link PlayerCharacter}
     */
    void use(PlayerCharacter character);
}
