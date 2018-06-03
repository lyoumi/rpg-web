package com.web.rpg.model.Items.impl.heal.healManaPoint;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.impl.HealingItems;
import com.web.rpg.model.Items.impl.heal.HealingItemsList;

public interface HealingManaPointItems extends HealingItems{
    @Override
    int getPrice();

    @Override
    HealingItemsList getHealingItemClass();

    @Override
    void use(PlayerCharacter character);
}
