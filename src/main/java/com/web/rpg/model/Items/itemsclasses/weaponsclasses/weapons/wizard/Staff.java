package com.web.rpg.model.Items.itemsclasses.weaponsclasses.weapons.wizard;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.EquipmentItems;
import com.web.rpg.model.Items.itemsclasses.weaponsclasses.Weapons;
import com.web.rpg.model.abilities.Magic;
import com.web.rpg.model.abilities.buffs.buffsclasses.WizardBuff;

import java.util.Random;

public class Staff implements Weapons{
    private int damage;
    private int itemLevel;
    private PlayerCharacter character;
    private Magic magic;
    private int price;
    private String equipment;

    private Random random = new Random();

    private Staff(PlayerCharacter character){
        this.character = character;
        this.itemLevel = random.nextInt(character.getLevel() + 1);
        this.price = 100 * itemLevel;
        this.damage = itemLevel * 6 + 5;
        this.magic = WizardBuff.magicFactory.getMagicFactory(character.getLevel());
        this.equipment = EquipmentItems.HANDS.name();
    }

    @Override
    public EquipmentItems getEquipment() {
        return EquipmentItems.HANDS;
    }
}