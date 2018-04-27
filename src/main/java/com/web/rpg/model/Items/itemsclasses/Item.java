package com.web.rpg.model.Items.itemsclasses;

/**
 * Created by pikachu on 17.07.17.
 */

import com.web.rpg.model.Items.EquipmentSlot;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.UUID;

/**
 * Item entity-class
 */

@Data
@Document
public class Item implements Serializable {
    @Id
    private UUID id;
    private String name;
    private Double activePoints;
    private EquipmentSlot slot;
}
