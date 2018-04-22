package com.web.rpg.model.Items.itemsclasses.weaponsclasses.weapons.archer;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.EquipmentItems;
import com.web.rpg.model.Items.itemsclasses.ItemsFactory;
import com.web.rpg.model.Items.itemsclasses.weaponsclasses.Weapons;
import com.web.rpg.model.abilities.Magic;
import com.web.rpg.model.abilities.buffs.buffsclasses.ArchersBuff;
import com.web.rpg.model.abilities.debuffs.debuffsclasses.damage.BurningJoe;

import java.util.Random;

public class LegendaryBow implements Weapons {
    private int damage;
    private int itemLevel;
    private PlayerCharacter character;
    private Magic buff;
    private Magic magic;
    private int price;
    private String equipment;

    private Random random = new Random();

    private LegendaryBow(PlayerCharacter character){
        this.character = character;
        this.itemLevel = character.getLevel() + 5;
        this.damage = itemLevel * 9;
        this.price = 10000 * itemLevel;
        this.buff = ArchersBuff.magicFactory.getMagicFactory(character.getLevel());
        this.magic = BurningJoe.magicFactory.getMagicFactory(itemLevel);
        this.equipment = EquipmentItems.HANDS.name();
    }

    @Override
    public EquipmentItems getEquipment() {
        return EquipmentItems.HANDS;
    }

    public static ItemsFactory itemsFactory = LegendaryBow::new;
}
