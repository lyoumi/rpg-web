package com.web.rpg.model.Items.itemsclasses.weaponsclasses.weapons.archer;

import com.web.rpg.model.Characters.Character;
import com.web.rpg.model.Items.EquipmentItems;
import com.web.rpg.model.Items.itemsclasses.ItemsFactory;
import com.web.rpg.model.Items.itemsclasses.weaponsclasses.Weapons;
import com.web.rpg.model.abilities.Magic;
import com.web.rpg.model.abilities.buffs.buffsclasses.ArchersBuff;

import java.util.Random;

/**
 * Created by pikachu on 17.07.17.
 */
public class Bow implements Weapons {
    private int damage;
    private int itemLevel;
    private Character character;
    private Magic magic;
    private final int price;

    private Random random = new Random();

    private Bow(Character character){
        this.character = character;
        this.itemLevel = random.nextInt(character.getLevel() + 1);
        this.price = 100* getItemLevel();
        this.damage = getItemLevel() * 7 + 5;
        this.magic = ArchersBuff.magicFactory.getMagicFactory(character.getLevel());
    }

    @Override
    public Magic getBuff() {
        return magic;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public EquipmentItems EQUIPMENT_ITEMS() {
        return EquipmentItems.HANDS;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getItemLevel() {
        return itemLevel;
    }

    @Override
    public String getName() {
        return Bow.class.getSimpleName();
    }

    @Override
    public String toString(){
        return getName() + ": " + " ATK +" + getDamage();
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
    }

    public static ItemsFactory itemsFactory = Bow::new;
}
