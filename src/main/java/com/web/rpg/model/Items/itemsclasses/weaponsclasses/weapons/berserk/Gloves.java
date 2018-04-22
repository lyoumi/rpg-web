package com.web.rpg.model.Items.itemsclasses.weaponsclasses.weapons.berserk;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.EquipmentItems;
import com.web.rpg.model.Items.itemsclasses.weaponsclasses.Weapons;
import com.web.rpg.model.abilities.Magic;
import com.web.rpg.model.abilities.buffs.buffsclasses.BerserkBuff;

import java.util.Random;

public class Gloves implements Weapons{
    private int damage;
    private int itemLevel;
    private PlayerCharacter character;
    private Magic magic;
    private int price;
    private String equipment;

    private Random random = new Random();

    private Gloves(PlayerCharacter character){
        this.character = character;
        this.itemLevel = random.nextInt(character.getLevel() + 1);
        this.price = 100 * itemLevel;
        this.damage = itemLevel * 3 + 5;
        this.magic = BerserkBuff.magicFactory.getMagicFactory(character.getLevel());
        this.equipment = EquipmentItems.HANDS.name();

    }

    @Override
    public EquipmentItems getEquipment() {
        return EquipmentItems.HANDS;
    }
}
