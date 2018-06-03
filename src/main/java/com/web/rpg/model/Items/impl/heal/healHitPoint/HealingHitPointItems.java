package com.web.rpg.model.Items.impl.heal.healHitPoint;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.impl.HealingItems;
import com.web.rpg.model.Items.impl.heal.HealingItemsList;

public interface HealingHitPointItems extends HealingItems{
    @Override
    int getPrice();

    @Override
    HealingItemsList getHealingItemClass();

    @Override
    void use(PlayerCharacter character);
}
