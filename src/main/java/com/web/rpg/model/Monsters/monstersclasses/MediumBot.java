package com.web.rpg.model.Monsters.monstersclasses;

import com.web.rpg.model.Monsters.Monster;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class MediumBot implements Monster {
    @Id
    private UUID id;
    private int level;
    private int damage;
    private double hitPoint;
    private int experience;
    private int gold = 1000;
    private String name;
}
