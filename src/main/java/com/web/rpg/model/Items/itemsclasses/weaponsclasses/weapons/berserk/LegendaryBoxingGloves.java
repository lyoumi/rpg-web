package com.web.rpg.model.Items.itemsclasses.weaponsclasses.weapons.berserk;

import com.web.rpg.model.Characters.Character;
import com.web.rpg.model.Items.EquipmentItems;
import com.web.rpg.model.Items.itemsclasses.ItemsFactory;
import com.web.rpg.model.Items.itemsclasses.weaponsclasses.Weapons;
import com.web.rpg.model.abilities.Magic;
import com.web.rpg.model.abilities.buffs.buffsclasses.BerserkBuff;

import java.util.Random;

public class LegendaryBoxingGloves implements Weapons{
    private int damage;
    private int itemLevel;
    private Character character;
    private Magic magic;
    private final int price;

    private Random random = new Random();

    private LegendaryBoxingGloves(Character character){
        this.character = character;
        this.itemLevel = character.getLevel() + 5;
        this.price = 10000 * getItemLevel();
        this.damage = getItemLevel() * 7 + 5;
        this.magic = BerserkBuff.magicFactory.getMagicFactory(character.getLevel());
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
        return LegendaryBoxingGloves.class.getSimpleName();
    }

    @Override
    public String toString(){
        return getName() + ": " + " ATK +" + getDamage();
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
    }

    public static ItemsFactory itemsFactory = LegendaryBoxingGloves::new;
}
