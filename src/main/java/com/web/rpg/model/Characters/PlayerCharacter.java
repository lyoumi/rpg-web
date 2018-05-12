package com.web.rpg.model.Characters;

import com.web.rpg.model.Items.impl.Item;
import com.web.rpg.model.Monsters.Monster;
import com.web.rpg.model.Quests.Quest;
import com.web.rpg.model.abilities.Magic;
import com.web.rpg.model.abilities.buffs.BuffMagic;
import com.web.rpg.model.cities.City;
import com.web.rpg.service.world.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Created by pikachu on 13.07.17.
 */

/**
 * Basic character model class
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerCharacter implements Serializable{

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

    List<Item> items;
    private Magic magic;
    private Integer magicPoint;

    private Integer expToNextLevel;
    private Integer gold;
    private BuffMagic buffMagic;
    private Integer additionPower;

    private Integer additionIntelligence;
    private Integer additionAgility;

    private Integer countOfBigHitPointBottle;
    private Integer countOfMiddleHitPointBottle;
    private Integer countOfSmallHitPointBottle;

    private Integer countOfBigManaPointBottles;
    private Integer countOfMiddleManaPointBottles;
    private Integer countOfSmallManaPointBottles;

    private Quest quest;

    private String currentAction;
    private Event actionType;
    private Integer countToEndOfAction;

    private Monster monster;
    private Story story;

    private City currentCity;
    private City targetCity;
    private Integer stepsToCity;
}
