package com.web.rpg.service.world.util;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.EquipmentSlot;
import com.web.rpg.model.Items.itemsclasses.Item;

import java.util.UUID;

public class ItemGenerator {


    public static Item generateItem(PlayerCharacter character) {
        Item item = new Item();
        item.setId(UUID.randomUUID());
        item.setActivePoints(20D);
        item.setName("Escalibur");
        item.setSlot(EquipmentSlot.HANDS);
        return item;
    }
}
