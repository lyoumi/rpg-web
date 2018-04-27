package com.web.rpg.model.Monsters.monstersclasses;

import com.web.rpg.model.Monsters.Monster;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.UUID;

@Data
@Document
public class MediumBot implements Monster, Serializable {
    @Id
    private UUID id;
    private int level;
    private int damage;
    private double hitPoint;
    private int experience;
    private int gold = 1000;
    private String name;
}
