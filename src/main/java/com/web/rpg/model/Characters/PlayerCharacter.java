package com.web.rpg.model.Characters;

import com.web.rpg.model.Items.impl.Item;
import com.web.rpg.model.Monsters.Monster;
import com.web.rpg.model.Quests.Quest;
import com.web.rpg.model.abilities.Magic;
import com.web.rpg.model.abilities.buffs.BuffMagic;
import com.web.rpg.model.cities.City;
import com.web.rpg.service.world.util.Event;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAgility() {
        return agility;
    }

    public void setAgility(Integer agility) {
        this.agility = agility;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Integer intelligence) {
        this.intelligence = intelligence;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Long getExperience() {
        return experience;
    }

    public void setExperience(Long experience) {
        this.experience = experience;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public Double getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(Double hitPoints) {
        this.hitPoints = hitPoints;
    }

    public Double getMaxHitPoints() {
        return maxHitPoints;
    }

    public void setMaxHitPoints(Double maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
    }

    public Double getManaPoints() {
        return manaPoints;
    }

    public void setManaPoints(Double manaPoints) {
        this.manaPoints = manaPoints;
    }

    public Double getMaxManaPoints() {
        return maxManaPoints;
    }

    public void setMaxManaPoints(Double maxManaPoints) {
        this.maxManaPoints = maxManaPoints;
    }

    public Double getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(Double baseDamage) {
        this.baseDamage = baseDamage;
    }

    public Integer getDefence() {
        return defence;
    }

    public void setDefence(Integer defence) {
        this.defence = defence;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Magic getMagic() {
        return magic;
    }

    public void setMagic(Magic magic) {
        this.magic = magic;
    }

    public Integer getMagicPoint() {
        return magicPoint;
    }

    public void setMagicPoint(Integer magicPoint) {
        this.magicPoint = magicPoint;
    }

    public Integer getExpToNextLevel() {
        return expToNextLevel;
    }

    public void setExpToNextLevel(Integer expToNextLevel) {
        this.expToNextLevel = expToNextLevel;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public BuffMagic getBuffMagic() {
        return buffMagic;
    }

    public void setBuffMagic(BuffMagic buffMagic) {
        this.buffMagic = buffMagic;
    }

    public Integer getAdditionPower() {
        return additionPower;
    }

    public void setAdditionPower(Integer additionPower) {
        this.additionPower = additionPower;
    }

    public Integer getAdditionIntelligence() {
        return additionIntelligence;
    }

    public void setAdditionIntelligence(Integer additionIntelligence) {
        this.additionIntelligence = additionIntelligence;
    }

    public Integer getAdditionAgility() {
        return additionAgility;
    }

    public void setAdditionAgility(Integer additionAgility) {
        this.additionAgility = additionAgility;
    }

    public Integer getCountOfBigHitPointBottle() {
        return countOfBigHitPointBottle;
    }

    public void setCountOfBigHitPointBottle(Integer countOfBigHitPointBottle) {
        this.countOfBigHitPointBottle = countOfBigHitPointBottle;
    }

    public Integer getCountOfMiddleHitPointBottle() {
        return countOfMiddleHitPointBottle;
    }

    public void setCountOfMiddleHitPointBottle(Integer countOfMiddleHitPointBottle) {
        this.countOfMiddleHitPointBottle = countOfMiddleHitPointBottle;
    }

    public Integer getCountOfSmallHitPointBottle() {
        return countOfSmallHitPointBottle;
    }

    public void setCountOfSmallHitPointBottle(Integer countOfSmallHitPointBottle) {
        this.countOfSmallHitPointBottle = countOfSmallHitPointBottle;
    }

    public Integer getCountOfBigManaPointBottles() {
        return countOfBigManaPointBottles;
    }

    public void setCountOfBigManaPointBottles(Integer countOfBigManaPointBottles) {
        this.countOfBigManaPointBottles = countOfBigManaPointBottles;
    }

    public Integer getCountOfMiddleManaPointBottles() {
        return countOfMiddleManaPointBottles;
    }

    public void setCountOfMiddleManaPointBottles(Integer countOfMiddleManaPointBottles) {
        this.countOfMiddleManaPointBottles = countOfMiddleManaPointBottles;
    }

    public Integer getCountOfSmallManaPointBottles() {
        return countOfSmallManaPointBottles;
    }

    public void setCountOfSmallManaPointBottles(Integer countOfSmallManaPointBottles) {
        this.countOfSmallManaPointBottles = countOfSmallManaPointBottles;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public String getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }

    public Event getActionType() {
        return actionType;
    }

    public void setActionType(Event actionType) {
        this.actionType = actionType;
    }

    public Integer getCountToEndOfAction() {
        return countToEndOfAction;
    }

    public void setCountToEndOfAction(Integer countToEndOfAction) {
        this.countToEndOfAction = countToEndOfAction;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public City getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(City currentCity) {
        this.currentCity = currentCity;
    }

    public City getTargetCity() {
        return targetCity;
    }

    public void setTargetCity(City targetCity) {
        this.targetCity = targetCity;
    }

    public Integer getStepsToCity() {
        return stepsToCity;
    }

    public void setStepsToCity(Integer stepsToCity) {
        this.stepsToCity = stepsToCity;
    }
}
