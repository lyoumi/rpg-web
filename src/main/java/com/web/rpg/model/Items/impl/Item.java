package com.web.rpg.model.Items.impl;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getActivePoints() {
        return activePoints;
    }

    public void setActivePoints(Double activePoints) {
        this.activePoints = activePoints;
    }

    public EquipmentSlot getSlot() {
        return slot;
    }

    public void setSlot(EquipmentSlot slot) {
        this.slot = slot;
    }
}
