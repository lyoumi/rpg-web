package com.web.rpg.model.Items.itemsclasses.armorsclasses.armors;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.EquipmentItems;
import com.web.rpg.model.Items.itemsclasses.armorsclasses.Armor;
import com.web.rpg.model.abilities.Magic;
import com.web.rpg.model.abilities.buffs.buffsclasses.ArmorBuff;

public class RelicArmor implements Armor {

    private int defence;
    private int itemLevel;
    private PlayerCharacter character;
    private Magic magic;
    private final int price;
    private String equipment;

    private RelicArmor(PlayerCharacter character){
        this.character = character;
        this.itemLevel = character.getLevel() + 20;
        this.price = itemLevel*10000;
        this.defence = itemLevel * 10 + 5;
        this.magic = ArmorBuff.magicFactory.getMagicFactory(itemLevel);
        this.equipment = EquipmentItems.ARMOR.name();
    }

    @Override
    public EquipmentItems getEquipment() {
        return EquipmentItems.ARMOR;
    }
}
