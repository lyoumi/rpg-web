package com.web.rpg.model.Items.itemsclasses.healclasses.healManaPoint.items;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.itemsclasses.healclasses.HealingItemsList;
import com.web.rpg.model.Items.itemsclasses.healclasses.healManaPoint.HealingManaPointItems;
import com.web.rpg.model.Items.itemsclasses.healclasses.healManaPoint.HealingManaPointItemsFactory;
import org.springframework.stereotype.Component;

@Component
public class SmallManaPointBottle implements HealingManaPointItems{

    private final int price;

    private SmallManaPointBottle(){
        this.price = 100;
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
        character.setManaPoints(character.getManaPoints() + character.getMaxManaPoints()/4);
    }

    public static HealingManaPointItemsFactory healingManaPointItemsFactory = SmallManaPointBottle::new;

    public String toString(){
        return SmallManaPointBottle.class.getSimpleName();
    }
}
