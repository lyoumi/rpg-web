package com.web.rpg.model.Items.itemsclasses.armorsclasses.helmets;

import com.web.rpg.model.Characters.Character;
import com.web.rpg.model.Items.EquipmentItems;
import com.web.rpg.model.Items.itemsclasses.ItemsFactory;
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
    private Character character;
    private Magic magic;
    private final int price;

    private Random random = new Random();

    private IronHelmet(Character character){
        this.character = character;
        this.itemLevel = random.nextInt(character.getLevel() + 1);
        this.price = 100*getItemLevel();
        this.defence = getItemLevel() * 10 + 5;
        this.magic = ArmorBuff.magicFactory.getMagicFactory(getItemLevel());
    }

    @Override
    public EquipmentItems EQUIPMENT_ITEMS() {
        return EquipmentItems.HEAD;
    }

    @Override
    public int getItemLevel() {
        return itemLevel;
    }

    @Override
    public int getDefence() {
        return defence;
    }

    @Override
    public String getName() {
        return IronHelmet.class.getSimpleName();
    }

    @Override
    public Magic getBuff() {
        return magic;
    }

    @Override
    public int getPrice() {
        return 0;
    }

    public String toString(){
        return IronHelmet.class.getSimpleName() + ": DEF +" + getDefence();
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
    }

    public static ItemsFactory itemsFactory = IronHelmet::new;
}
