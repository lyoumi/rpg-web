package com.web.rpg.service.world.util;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.EquipmentSlot;
import com.web.rpg.model.Items.impl.Item;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

@Component
public class ItemGenerator {

    private static final List<EquipmentSlot> EQUIPMENT_SLOTS = Arrays.asList(EquipmentSlot.values());
    private static final Random RANDOM = new Random();

    public Item generateItem(PlayerCharacter character) {
        EquipmentSlot currentEquipmentSlot = EQUIPMENT_SLOTS.get(RANDOM.nextInt(EQUIPMENT_SLOTS.size()));
        String name = generateName(currentEquipmentSlot.name());

        Item item = new Item();


        item.setId(UUID.randomUUID());
        item.setActivePoints(20D);
        item.setName(name);
        item.setSlot(currentEquipmentSlot);
        return item;
    }

    private String generateName(String prefix) {
        Properties prop = new Properties();
        List<String> itemNames = new ArrayList<>();
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream("properties/item-names.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Map.Entry<Object, Object> entry : prop.entrySet()) {
            if (StringUtils.containsIgnoreCase(entry.getKey().toString(), prefix)) {
                itemNames.add((String) entry.getValue());
            }
        }
        return itemNames.get(RANDOM.nextInt(itemNames.size()));
    }
}
