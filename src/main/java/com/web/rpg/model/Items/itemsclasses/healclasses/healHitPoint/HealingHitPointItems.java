package com.web.rpg.model.Items.itemsclasses.healclasses.healHitPoint;

import com.web.rpg.model.Characters.Character;
import com.web.rpg.model.Items.itemsclasses.HealingItems;
import com.web.rpg.model.Items.itemsclasses.healclasses.HealingItemsList;

public interface HealingHitPointItems extends HealingItems{
    @Override
    int getPrice();

    @Override
    HealingItemsList getHealingItemClass();

    @Override
    void use(Character character);
}
