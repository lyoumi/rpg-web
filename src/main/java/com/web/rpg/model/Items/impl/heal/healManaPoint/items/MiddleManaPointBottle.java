package com.web.rpg.model.Items.impl.heal.healManaPoint.items;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.impl.heal.HealingItemsList;
import com.web.rpg.model.Items.impl.heal.healManaPoint.HealingManaPointItems;
import com.web.rpg.model.Items.impl.heal.healManaPoint.HealingManaPointItemsFactory;
import org.springframework.stereotype.Component;

@Component
public class MiddleManaPointBottle implements HealingManaPointItems{

    private final int price;

    private MiddleManaPointBottle(){
        this.price = 150;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public HealingItemsList getHealingItemClass() {
        return HealingItemsList.MiddleFlower;
    }

    @Override
    public void use(PlayerCharacter character) {
        character.setManaPoints(character.getManaPoints() + character.getMaxManaPoints()/2);
    }

    public static HealingManaPointItemsFactory healingManaPointItemsFactory = MiddleManaPointBottle::new;

    public String toString(){
        return MiddleManaPointBottle.class.getSimpleName();
    }
}
