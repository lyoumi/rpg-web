package com.web.rpg.model.Monsters.equipment;

import com.web.rpg.model.Characters.Character;
import com.web.rpg.model.Items.EquipmentItems;
import com.web.rpg.model.Items.itemsclasses.HealingItems;
import com.web.rpg.model.Items.itemsclasses.Item;

import java.util.HashMap;
import java.util.List;

public interface MonsterEquipment {

    HashMap<EquipmentItems, Item> initEquipment(Character character);

    List<HealingItems> initializeItemList();
}
