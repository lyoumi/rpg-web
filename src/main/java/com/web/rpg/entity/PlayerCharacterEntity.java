package com.web.rpg.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.UUID;

@Data
@Document
public class PlayerCharacterEntity implements Serializable {

    @Id
    private UUID id;
    private UUID playerId;

    private String name;

    private Integer agility;
    private Integer intelligence;
    private Integer power;

    private Long experience;
    private Integer level;

    private String characterClass;

    private Double hitPoints;
    private Double maxHitPoints;
    private Double manaPoints;
    private Double maxManaPoints;

    private Double baseDamage;
    private Integer defence;

    private byte[] items;
    private byte[] magic;
    private Integer magicPoint;

    private Integer expToNextLevel;
    private Integer gold;
    private byte[] buffMagic;

    private Integer additionPower;
    private Integer additionIntelligence;
    private Integer additionAgility;

    private Integer countOfBigHitPointBottle;
    private Integer countOfMiddleHitPointBottle;
    private Integer countOfSmallHitPointBottle;

    private Integer countOfBigManaPointBottles;
    private Integer countOfMiddleManaPointBottles;
    private Integer countOfSmallManaPointBottles;

    private byte[] quest;

    private String currentAction;
    private String actionType;
    private Integer countToEndOfAction;

    private byte[] monster;
    private byte[] stories;
}
