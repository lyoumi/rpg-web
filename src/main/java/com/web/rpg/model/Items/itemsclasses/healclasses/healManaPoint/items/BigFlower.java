package com.web.rpg.model.Items.itemsclasses.healclasses.healManaPoint.items;

import com.web.rpg.model.Characters.Character;
import com.web.rpg.model.Items.itemsclasses.healclasses.HealingItemsList;
import com.web.rpg.model.Items.itemsclasses.healclasses.healManaPoint.HealingManaPointItems;
import com.web.rpg.model.Items.itemsclasses.healclasses.healManaPoint.HealingManaPointItemsFactory;

public class BigFlower implements HealingManaPointItems {

    private final int price;

    private BigFlower(){
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
    public void use(Character character) {
        character.setManaPoint(character.getMaxManaPoint());
    }

    public static HealingManaPointItemsFactory healingHitPointItemsFactory = BigFlower::new;

    @Override
    public void finalize() throws Throwable {
        super.finalize();
    }

    public String toString(){
        return BigFlower.class.getSimpleName();
    }
}
