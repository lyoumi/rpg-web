package com.web.rpg.model.Items.itemsclasses.armorsclasses.helmets;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.EquipmentItems;
import com.web.rpg.model.Items.itemsclasses.armorsclasses.Armor;
import com.web.rpg.model.abilities.Magic;
import com.web.rpg.model.abilities.buffs.buffsclasses.ArmorBuff;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class LegendaryHelmet implements Armor {

    @Id
    private UUID id;
    private int defence;
    private int itemLevel;
    private PlayerCharacter character;
    private Magic magic;
    private int price;
    private String equipment;

    private LegendaryHelmet(PlayerCharacter character){
        this.character = character;
        this.itemLevel = character.getLevel() + 5;
        this.price = 10000 * itemLevel;
        this.defence = itemLevel * 10 + 5;
        this.magic = ArmorBuff.magicFactory.getMagicFactory(itemLevel);
        this.equipment = EquipmentItems.HEAD.name();
    }

    @Override
    public EquipmentItems getEquipment() {
        return EquipmentItems.HEAD;
    }
}