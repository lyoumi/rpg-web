package com.web.rpg.model.Monsters.monstersclasses;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.UUID;

@Data
@Document
public class Monster implements Serializable {
    @Id
    private UUID monsterId;
    private int level;
    private int damage;
    private double hitPoint;
    private int experience;
    private int gold = 1000;
    private String name;
}
