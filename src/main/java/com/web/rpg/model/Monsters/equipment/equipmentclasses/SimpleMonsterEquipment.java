package com.web.rpg.model.Monsters.equipment.equipmentclasses;

import com.web.rpg.model.Characters.Character;
import com.web.rpg.model.Characters.characterclasses.Archer;
import com.web.rpg.model.Characters.characterclasses.Berserk;
import com.web.rpg.model.Items.EquipmentItems;
import com.web.rpg.model.Items.itemsclasses.HealingItems;
import com.web.rpg.model.Items.itemsclasses.Item;
import com.web.rpg.model.Items.itemsclasses.armorsclasses.armors.IronChest;
import com.web.rpg.model.Items.itemsclasses.armorsclasses.boots.IronBoots;
import com.web.rpg.model.Items.itemsclasses.armorsclasses.helmets.IronHelmet;
import com.web.rpg.model.Items.itemsclasses.healclasses.healHitPoint.items.BigHPBottle;
import com.web.rpg.model.Items.itemsclasses.healclasses.healHitPoint.items.MiddleHPBottle;
import com.web.rpg.model.Items.itemsclasses.healclasses.healHitPoint.items.SmallHPBottle;
import com.web.rpg.model.Items.itemsclasses.healclasses.healManaPoint.items.BigFlower;
import com.web.rpg.model.Items.itemsclasses.healclasses.healManaPoint.items.MiddleFlower;
import com.web.rpg.model.Items.itemsclasses.healclasses.healManaPoint.items.SmallFlower;
import com.web.rpg.model.Items.itemsclasses.weaponsclasses.weapons.archer.Bow;
import com.web.rpg.model.Items.itemsclasses.weaponsclasses.weapons.archer.Sword;
import com.web.rpg.model.Items.itemsclasses.weaponsclasses.weapons.berserk.BoxingGloves;
import com.web.rpg.model.Items.itemsclasses.weaponsclasses.weapons.berserk.Gloves;
import com.web.rpg.model.Items.itemsclasses.weaponsclasses.weapons.wizard.MagicWand;
import com.web.rpg.model.Items.itemsclasses.weaponsclasses.weapons.wizard.Staff;
import com.web.rpg.model.Monsters.equipment.MonsterEquipment;
import com.web.rpg.model.Monsters.equipment.MonsterEquipmentFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SimpleMonsterEquipment implements MonsterEquipment {

    private Random random = new Random();

    public HashMap<EquipmentItems, Item> initEquipment(Character character){
        HashMap<EquipmentItems, Item> equipment = new HashMap<>();
        int i = random.nextInt(10);
        if (character instanceof Archer){
            if ((i > 0) && (i < 3))
                equipment.put(Bow.itemsFactory.createNewItem(character).EQUIPMENT_ITEMS(), Bow.itemsFactory.createNewItem(character));
            else
                equipment.put(Sword.itemsFactory.createNewItem(character).EQUIPMENT_ITEMS(), Sword.itemsFactory.createNewItem(character));
        } else if (character instanceof Berserk){
            if ((i > 0) && (i < 3))
                equipment.put(BoxingGloves.itemsFactory.createNewItem(character).EQUIPMENT_ITEMS(), BoxingGloves.itemsFactory.createNewItem(character));
            else
                equipment.put(Gloves.itemsFactory.createNewItem(character).EQUIPMENT_ITEMS(), Gloves.itemsFactory.createNewItem(character));
        } else {
            if ((i > 0) && (i < 3))
                equipment.put(Staff.itemsFactory.createNewItem(character).EQUIPMENT_ITEMS(), Staff.itemsFactory.createNewItem(character));
            else
                equipment.put(MagicWand.itemsFactory.createNewItem(character).EQUIPMENT_ITEMS(), MagicWand.itemsFactory.createNewItem(character));
        }
        equipment.put(IronHelmet.itemsFactory.createNewItem(character).EQUIPMENT_ITEMS(), IronHelmet.itemsFactory.createNewItem(character));
        equipment.put(IronChest.itemsFactory.createNewItem(character).EQUIPMENT_ITEMS(), IronChest.itemsFactory.createNewItem(character));
        equipment.put(IronBoots.itemsFactory.createNewItem(character).EQUIPMENT_ITEMS(), IronBoots.itemsFactory.createNewItem(character));
        return equipment;
    }

    public List<HealingItems> initializeItemList(){
        List<HealingItems> itemsList = new ArrayList<>();
        itemsList.add(BigHPBottle.healingHitPointItemsFactory.getNewHealingHitPointItem());
        itemsList.add(BigFlower.healingHitPointItemsFactory.getNewHealingManaPointItem());
        itemsList.add(MiddleHPBottle.healingHitPointItemsFactory.getNewHealingHitPointItem());
        itemsList.add(MiddleFlower.healingManaPointItemsFactory.getNewHealingManaPointItem());
        itemsList.add(SmallHPBottle.healingHitPointItemsFactory.getNewHealingHitPointItem());
        itemsList.add(SmallFlower.healingManaPointItemsFactory.getNewHealingManaPointItem());

        return  itemsList;
    }




    public static MonsterEquipmentFactory monsterEquipmentFactory = SimpleMonsterEquipment::new;
}
