package com.web.rpg.model.Items.impl.heal.healHitPoint.items;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.impl.heal.HealingItemsList;
import com.web.rpg.model.Items.impl.heal.healHitPoint.HealingHitPointItems;
import com.web.rpg.model.Items.impl.heal.healHitPoint.HealingHitPointItemsFactory;
import org.springframework.stereotype.Component;

@Component
public class SmallHPBottle implements HealingHitPointItems {

    private final int price;

    private SmallHPBottle(){
        this.price = 100;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public HealingItemsList getHealingItemClass() {
        return HealingItemsList.SmallHPBottle;
    }

    @Override
    public void use(PlayerCharacter character) {
        character.setHitPoints(character.getHitPoints() + character.getMaxHitPoints()/4);
    }

    public static HealingHitPointItemsFactory healingHitPointItemsFactory = SmallHPBottle::new;

    public String toString(){
        return SmallHPBottle.class.getSimpleName();
    }
}
