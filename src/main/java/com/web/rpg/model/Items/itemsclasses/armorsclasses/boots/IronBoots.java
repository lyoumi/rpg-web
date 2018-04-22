package com.web.rpg.model.Items.itemsclasses.armorsclasses.boots;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.EquipmentItems;
import com.web.rpg.model.Items.itemsclasses.armorsclasses.Armor;
import com.web.rpg.model.abilities.Magic;
import com.web.rpg.model.abilities.buffs.buffsclasses.ArmorBuff;

import java.util.Random;

/**
 * Created by pikachu on 17.07.17.
 */
public class IronBoots implements Armor {

    private int defence;
    private int itemLevel;
    private Magic magic;
    private PlayerCharacter character;
    private final int price;
    private String equipment;

    private Random random = new Random();

    private IronBoots(PlayerCharacter character){
        this.character = character;
        this.itemLevel = random.nextInt(character.getLevel() + 1);
        this.price = 100 * itemLevel;
        this.defence = itemLevel * 10 + 5;
        this.magic = ArmorBuff.magicFactory.getMagicFactory(itemLevel);
        this.equipment = EquipmentItems.LEGS.name();
    }

    @Override
    public EquipmentItems getEquipment() {
        return EquipmentItems.ARMOR;
    }
}
