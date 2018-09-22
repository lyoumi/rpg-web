package com.web.rpg.service.world.util

import java.io.IOException
import java.util
import java.util.{Properties, Random, UUID}

import com.web.rpg.model.Characters.PlayerCharacter
import com.web.rpg.model.Items.EquipmentSlot
import com.web.rpg.model.Items.impl.Item
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Component

@Component
class ItemGenerator {
  private val EQUIPMENT_SLOTS : List[EquipmentSlot] = EquipmentSlot.values.toList
  private val RANDOM : Random = new Random

  def generateItem(character: PlayerCharacter): Item = {
    val currentEquipmentSlot : EquipmentSlot = EQUIPMENT_SLOTS(RANDOM.nextInt(EQUIPMENT_SLOTS.size))
    val name = generateName(currentEquipmentSlot.name)
    val item : Item = new Item
    item.setId(UUID.randomUUID)
    item.setActivePoints(20D)
    item.setName(name)
    item.setSlot(currentEquipmentSlot)
    item
  }

  private def generateName(prefix: String) = {
    val prop = new Properties
    val itemNames = new util.ArrayList[String]
    try
      prop.load(getClass.getClassLoader.getResourceAsStream("properties/item-names.properties"))
    catch {
      case e: IOException =>
        e.printStackTrace()
    }
    import scala.collection.JavaConversions._
    for (entry <- prop.entrySet) {
      if (StringUtils.containsIgnoreCase(entry.getKey.toString, prefix)) itemNames.add(entry.getValue.asInstanceOf[String])
    }
    itemNames.get(RANDOM.nextInt(itemNames.size))
  }
}
