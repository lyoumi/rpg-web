package com.web.rpg.model.Items.impl.heal.healHitPoint.items;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.impl.heal.HealingItemsList;
import com.web.rpg.model.Items.impl.heal.healHitPoint.HealingHitPointItems;
import com.web.rpg.model.Items.impl.heal.healHitPoint.HealingHitPointItemsFactory;
import org.springframework.stereotype.Component;

@Component
public class MiddleHPBottle implements HealingHitPointItems {

    private final int price;

    private MiddleHPBottle(){
        this.price = 150;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public HealingItemsList getHealingItemClass() {
        return HealingItemsList.MiddleHPBottle;
    }

    @Override
    public void use(PlayerCharacter character) {
        character.setHitPoints(character.getHitPoints() + character.getMaxHitPoints()/2);
    }

    public static HealingHitPointItemsFactory healingHitPointItemsFactory = MiddleHPBottle::new;

    public String toString(){
        return MiddleHPBottle.class.getSimpleName();
    }
}
