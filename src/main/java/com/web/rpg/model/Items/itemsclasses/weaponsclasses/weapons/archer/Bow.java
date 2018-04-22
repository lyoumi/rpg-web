package com.web.rpg.model.Items.itemsclasses.weaponsclasses.weapons.archer;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.EquipmentItems;
import com.web.rpg.model.Items.itemsclasses.weaponsclasses.Weapons;
import com.web.rpg.model.abilities.Magic;
import com.web.rpg.model.abilities.buffs.buffsclasses.ArchersBuff;
import lombok.Data;

import java.util.Random;

/**
 * Created by pikachu on 17.07.17.
 */
@Data
public class Bow implements Weapons {
    private int damage;
    private int itemLevel;
    private PlayerCharacter character;
    private Magic magic;
    private int price;
    private String equipment;

    private Random random = new Random();

    private Bow(PlayerCharacter character){
        this.character = character;
        this.itemLevel = random.nextInt(character.getLevel() + 1);
        this.price = 100* itemLevel;
        this.damage = itemLevel * 7 + 5;
        this.magic = ArchersBuff.magicFactory.getMagicFactory(character.getLevel());
        this.equipment = EquipmentItems.HANDS.name();
    }

    @Override
    public EquipmentItems getEquipment() {
        return EquipmentItems.HANDS;
    }
}
