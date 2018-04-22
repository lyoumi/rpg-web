package com.web.rpg.model.Items.itemsclasses.healclasses.healHitPoint.items;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.itemsclasses.healclasses.HealingItemsList;
import com.web.rpg.model.Items.itemsclasses.healclasses.healHitPoint.HealingHitPointItems;
import com.web.rpg.model.Items.itemsclasses.healclasses.healHitPoint.HealingHitPointItemsFactory;
import org.springframework.stereotype.Component;

@Component
public class BigHPBottle implements HealingHitPointItems {

    private final int price;

    private BigHPBottle(){
        this.price = 200;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public HealingItemsList getHealingItemClass() {
        return HealingItemsList.BigHPBottle;
    }

    @Override
    public void use(PlayerCharacter character) {
        character.setHitPoints(character.getMaxHitPoints());
    }

    public static HealingHitPointItemsFactory healingHitPointItemsFactory = BigHPBottle::new;

    public String toString(){
        return BigHPBottle.class.getSimpleName();
    }
}
