package com.web.rpg.model.Items.itemsclasses.armorsclasses.helmets;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.EquipmentItems;
import com.web.rpg.model.Items.itemsclasses.armorsclasses.Armor;
import com.web.rpg.model.abilities.Magic;
import com.web.rpg.model.abilities.buffs.buffsclasses.ArmorBuff;

import java.util.Random;

/**
 * Created by pikachu on 17.07.17.
 */
public class IronHelmet implements Armor{

    private int defence;
    private int itemLevel;
    private PlayerCharacter character;
    private Magic magic;
    private final int price;
    private String equipment;

    private Random random = new Random();

    private IronHelmet(PlayerCharacter character){
        this.character = character;
        this.itemLevel = random.nextInt(character.getLevel() + 1);
        this.price = 100 * itemLevel;
        this.defence = itemLevel * 10 + 5;
        this.magic = ArmorBuff.magicFactory.getMagicFactory(itemLevel);
        this.equipment = EquipmentItems.HEAD.name();
    }

    @Override
    public EquipmentItems getEquipment() {
        return EquipmentItems.HEAD;
    }
}
