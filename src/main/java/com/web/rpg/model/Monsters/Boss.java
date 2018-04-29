package com.web.rpg.model.Monsters;

import lombok.Data;

import java.util.UUID;

@Data
public class Boss {

//    @Id
    private UUID id;
    private int level;
    private int damage;
    private int hitPoint;
    private final int experience;
    private final int gold = 100000;
    private final String name;

//    private Boss(PlayerCharacter character){
//        this.character = character;
//        if (character instanceof Berserk) level = character.getLevel() + 4;
//        else level = character.getLevel() + 1;
//        if (character.getLevel() <= 6){
//            hitPoint = (level)*500;
//            damage = (level)*90;
//            experience = 500;
//        } else if (character.getLevel() == 9){
//            hitPoint = (level)*750;
//            damage = (level)*120;
//            experience = 1000 * 2;
//        } else if (character.getLevel() == 12){
//            hitPoint = (level)*1000;
//            damage = (level)*150;
//            experience = 1000 * character.getLevel() * 3;
//        } else if (character.getLevel() == 15){
//            hitPoint = (level)*1250;
//            damage = (level)*200;
//            experience = 1000 * character.getLevel() * 6;
//        } else {
//            hitPoint = (level)*1500;
//            damage = (level)*250;
//            experience = 1000 * character.getLevel() * 9;
//        }
//        setEquipmentOfDevil(character);
//        itemsList = SimpleMonsterEquipment.monsterEquipmentFactory.getMonsterEquipment().initializeItemList();
//        sizeOfItems = itemsList.size();
//        name = "Satan";
//    }
}