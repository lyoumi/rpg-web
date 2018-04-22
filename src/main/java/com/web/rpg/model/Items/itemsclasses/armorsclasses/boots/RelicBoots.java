package com.web.rpg.model.Items.itemsclasses.armorsclasses.boots;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.EquipmentItems;
import com.web.rpg.model.Items.itemsclasses.armorsclasses.Armor;
import com.web.rpg.model.abilities.Magic;
import com.web.rpg.model.abilities.buffs.buffsclasses.ArmorBuff;

public class RelicBoots implements Armor {
    private int defence;
    private int itemLevel;
    private Magic magic;
    private PlayerCharacter character;
    private final int price;
    private String equipment;

    private RelicBoots(PlayerCharacter character){
        this.character = character;
        this.itemLevel = character.getLevel() + 20;
        this.price = 10000 * itemLevel;
        this.defence = itemLevel * 10 + 5;
        this.magic = ArmorBuff.magicFactory.getMagicFactory(itemLevel);
        this.equipment = EquipmentItems.LEGS.name();
    }

    @Override
    public EquipmentItems getEquipment() {
        return EquipmentItems.LEGS;
    }
}
