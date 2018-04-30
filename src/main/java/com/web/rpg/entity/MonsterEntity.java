package com.web.rpg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class MonsterEntity {

    @Id
    private UUID id;
    private int level;
    private int damage;
    private double hitPoint;
    private int experience;
    private int gold = 1000;
    private String name;
}
