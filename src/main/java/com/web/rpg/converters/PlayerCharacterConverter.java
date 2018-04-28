package com.web.rpg.converters;

import com.web.rpg.entity.PlayerCharacterEntity;
import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Characters.Stories;
import com.web.rpg.model.Items.impl.Item;
import com.web.rpg.model.Monsters.monstersclasses.Monster;
import com.web.rpg.model.Quests.Quest;
import com.web.rpg.model.abilities.Magic;
import com.web.rpg.model.abilities.buffs.BuffMagic;
import com.web.rpg.service.world.Event;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class PlayerCharacterConverter {

    public PlayerCharacterEntity convertToEntity(PlayerCharacter character) {
        PlayerCharacterEntity characterEntity = new PlayerCharacterEntity();
        characterEntity.setId(character.getId());
        characterEntity.setPlayerId(character.getPlayerId());
        characterEntity.setName(character.getName());
        characterEntity.setAgility(character.getAgility());
        characterEntity.setIntelligence(character.getIntelligence());
        characterEntity.setPower(character.getPower());
        characterEntity.setExperience(character.getExperience());
        characterEntity.setLevel(character.getLevel());
        characterEntity.setCharacterClass(character.getCharacterClass());
        characterEntity.setHitPoints(character.getHitPoints());
        characterEntity.setMaxHitPoints(character.getMaxHitPoints());
        characterEntity.setManaPoints(character.getManaPoints());
        characterEntity.setMaxManaPoints(character.getMaxManaPoints());
        characterEntity.setBaseDamage(character.getBaseDamage());
        characterEntity.setDefence(character.getDefence());
        characterEntity.setItems(serializeObjectToByteArray(character.getItems()));
        characterEntity.setMagic(serializeObjectToByteArray(character.getMagic()));
        characterEntity.setMagicPoint(character.getMagicPoint());
        characterEntity.setExpToNextLevel(character.getExpToNextLevel());
        characterEntity.setGold(character.getGold());
        characterEntity.setBuffMagic(serializeObjectToByteArray(character.getBuffMagic()));
        characterEntity.setAdditionAgility(character.getAdditionAgility());
        characterEntity.setAdditionIntelligence(character.getAdditionIntelligence());
        characterEntity.setAdditionPower(character.getAdditionPower());
        characterEntity.setCountOfBigHitPointBottle(character.getCountOfBigHitPointBottle());
        characterEntity.setCountOfMiddleHitPointBottle(character.getCountOfMiddleHitPointBottle());
        characterEntity.setCountOfSmallHitPointBottle(character.getCountOfSmallHitPointBottle());
        characterEntity.setCountOfBigManaPointBottles(character.getCountOfBigManaPointBottles());
        characterEntity.setCountOfMiddleManaPointBottles(character.getCountOfMiddleManaPointBottles());
        characterEntity.setCountOfSmallManaPointBottles(character.getCountOfSmallManaPointBottles());
        characterEntity.setQuest(serializeObjectToByteArray(character.getQuest()));
        characterEntity.setCurrentAction(character.getCurrentAction());
        characterEntity.setActionType(character.getActionType().name());
        characterEntity.setCountToEndOfAction(character.getCountToEndOfAction());
        characterEntity.setMonster(serializeObjectToByteArray(character.getMonster()));
        characterEntity.setStories(serializeObjectToByteArray(character.getStories()));
        return characterEntity;
    }

    public PlayerCharacter convertFromEntity(PlayerCharacterEntity characterEntity) {
        PlayerCharacter character = new PlayerCharacter();
        character.setId(characterEntity.getId());
        character.setPlayerId(characterEntity.getPlayerId());
        character.setName(characterEntity.getName());
        character.setAgility(characterEntity.getAgility());
        character.setIntelligence(characterEntity.getIntelligence());
        character.setPower(characterEntity.getPower());
        character.setExperience(characterEntity.getExperience());
        character.setLevel(characterEntity.getLevel());
        character.setCharacterClass(characterEntity.getCharacterClass());
        character.setHitPoints(characterEntity.getHitPoints());
        character.setMaxHitPoints(characterEntity.getMaxHitPoints());
        character.setManaPoints(characterEntity.getManaPoints());
        character.setMaxManaPoints(characterEntity.getMaxManaPoints());
        character.setBaseDamage(characterEntity.getBaseDamage());
        character.setDefence(characterEntity.getDefence());
        character.setItems((List<Item>) deserializeObjectFromByte(characterEntity.getItems()));
        character.setMagic((Magic) deserializeObjectFromByte(characterEntity.getMagic()));
        character.setMagicPoint(characterEntity.getMagicPoint());
        character.setExpToNextLevel(characterEntity.getExpToNextLevel());
        character.setGold(characterEntity.getGold());
        character.setBuffMagic((BuffMagic) deserializeObjectFromByte(characterEntity.getBuffMagic()));
        character.setAdditionAgility(characterEntity.getAdditionAgility());
        character.setAdditionIntelligence(characterEntity.getAdditionIntelligence());
        character.setAdditionPower(characterEntity.getAdditionPower());
        character.setCountOfBigHitPointBottle(characterEntity.getCountOfBigHitPointBottle());
        character.setCountOfMiddleHitPointBottle(characterEntity.getCountOfMiddleHitPointBottle());
        character.setCountOfSmallHitPointBottle(characterEntity.getCountOfSmallHitPointBottle());
        character.setCountOfBigManaPointBottles(characterEntity.getCountOfBigManaPointBottles());
        character.setCountOfMiddleManaPointBottles(characterEntity.getCountOfMiddleManaPointBottles());
        character.setCountOfSmallManaPointBottles(characterEntity.getCountOfSmallManaPointBottles());
        character.setQuest((Quest) deserializeObjectFromByte(characterEntity.getQuest()));
        character.setCurrentAction(characterEntity.getCurrentAction());
        character.setActionType(Event.valueOf(characterEntity.getActionType()));
        character.setCountToEndOfAction(characterEntity.getCountToEndOfAction());
        character.setMonster((Monster) deserializeObjectFromByte(characterEntity.getMonster()));
        character.setStories((Stories) deserializeObjectFromByte(characterEntity.getStories()));
        return character;
    }

    private byte[] serializeObjectToByteArray(Object o) {
        if (o != null) {
            return SerializationUtils.serialize((Serializable) o);
        }
        return null;
    }

    private Object deserializeObjectFromByte(byte[] byteArray) {
        if (byteArray != null) {
            return SerializationUtils.deserialize(byteArray);
        }
        return null;
    }
}
