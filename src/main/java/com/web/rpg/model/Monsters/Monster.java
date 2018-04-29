package com.web.rpg.model.Monsters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
