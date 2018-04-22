package com.web.rpg.model.Items.itemsclasses.healclasses.healHitPoint.items;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.itemsclasses.healclasses.HealingItemsList;
import com.web.rpg.model.Items.itemsclasses.healclasses.healHitPoint.HealingHitPointItems;
import com.web.rpg.model.Items.itemsclasses.healclasses.healHitPoint.HealingHitPointItemsFactory;
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
