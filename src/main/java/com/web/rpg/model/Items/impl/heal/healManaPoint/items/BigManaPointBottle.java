package com.web.rpg.model.Items.impl.heal.healManaPoint.items;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.impl.heal.HealingItemsList;
import com.web.rpg.model.Items.impl.heal.healManaPoint.HealingManaPointItems;
import com.web.rpg.model.Items.impl.heal.healManaPoint.HealingManaPointItemsFactory;
import org.springframework.stereotype.Component;

@Component
public class BigManaPointBottle implements HealingManaPointItems {

    private final int price;

    private BigManaPointBottle(){
        this.price = 200;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public HealingItemsList getHealingItemClass() {
        return HealingItemsList.SmallFlower;
    }

    @Override
    public void use(PlayerCharacter character) {
        character.setManaPoints(character.getMaxManaPoints());
    }

    public static HealingManaPointItemsFactory healingHitPointItemsFactory = BigManaPointBottle::new;

    public String toString(){
        return BigManaPointBottle.class.getSimpleName();
    }
}
