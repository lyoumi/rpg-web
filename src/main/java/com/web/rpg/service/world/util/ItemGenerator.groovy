package com.web.rpg.service.world.util


import com.web.rpg.model.Items.EquipmentSlot
import com.web.rpg.model.Items.impl.Item
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Component

@Component
class ItemGenerator {
    private static final EQUIPMENT_SLOTS = Arrays.asList(EquipmentSlot.values())
    private static final RANDOM = new Random()

    Item generateItem() {
        def currentEquipmentSlot = EQUIPMENT_SLOTS.get(RANDOM.nextInt(EQUIPMENT_SLOTS.size()))
        def name = generateName(currentEquipmentSlot.name())

        def item = new Item()

        item.id = UUID.randomUUID()
        item.activePoints = 20D
        item.name = name
        item.slot = currentEquipmentSlot
        item
    }

    private generateName(String prefix) {
        def prop = new Properties()
        def itemNames = new ArrayList<>()
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream("properties/item-names.properties"))
        } catch (IOException e) {
            e.printStackTrace()
        }
        prop.entrySet()
                .each {
            Map.Entry<Object, Object> entry ->
                if (StringUtils.containsIgnoreCase(entry.getKey().toString(), prefix)) {
                    itemNames.add((String) entry.getValue())
                }
        }
        itemNames.get(RANDOM.nextInt(itemNames.size()))
    }
}
