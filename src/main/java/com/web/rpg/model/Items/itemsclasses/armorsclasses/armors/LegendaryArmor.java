package com.web.rpg.model.Items.itemsclasses.armorsclasses.armors;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.EquipmentItems;
import com.web.rpg.model.Items.itemsclasses.armorsclasses.Armor;
import com.web.rpg.model.abilities.Magic;
import com.web.rpg.model.abilities.buffs.buffsclasses.ArmorBuff;

public class LegendaryArmor implements Armor {

    private int defence;
    private int itemLevel;
    private PlayerCharacter character;
    private Magic magic;
    private final int price;
    private String equipment;

    private LegendaryArmor(PlayerCharacter character){
        this.character = character;
        this.itemLevel = character.getLevel() + 5;
        this.price = itemLevel * 10000;
        this.defence = this.itemLevel * 10 + 5;
        this.magic = ArmorBuff.magicFactory.getMagicFactory(itemLevel);
        this.equipment = EquipmentItems.HANDS.name();
    }

    @Override
    public EquipmentItems getEquipment() {
        return EquipmentItems.ARMOR;
    }
}
