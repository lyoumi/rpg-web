package com.web.rpg.model.Items.itemsclasses.weaponsclasses.weapons.wizard;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.EquipmentItems;
import com.web.rpg.model.Items.itemsclasses.weaponsclasses.Weapons;
import com.web.rpg.model.abilities.Magic;
import com.web.rpg.model.abilities.buffs.buffsclasses.WizardBuff;

import java.util.Random;

public class LegendaryStaff implements Weapons{
    private int damage;
    private int itemLevel;
    private PlayerCharacter character;
    private Magic magic;
    private int price;
    private String equipment;

    private Random random = new Random();

    private LegendaryStaff(PlayerCharacter character){
        this.character = character;
        this.itemLevel = character.getLevel() + 5;
        this.price = 10000 * itemLevel;
        this.damage = itemLevel * 8 + 5;
        this.magic = WizardBuff.magicFactory.getMagicFactory(character.getLevel());
        this.equipment = EquipmentItems.HANDS.name();
    }

    @Override
    public EquipmentItems getEquipment() {
        return EquipmentItems.HANDS;
    }
}
