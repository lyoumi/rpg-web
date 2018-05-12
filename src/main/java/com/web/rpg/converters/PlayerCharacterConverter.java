package com.web.rpg.converters;

import com.web.rpg.entity.PlayerCharacterEntity;
import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Characters.Story;
import com.web.rpg.model.Items.impl.Item;
import com.web.rpg.model.Monsters.Monster;
import com.web.rpg.model.Quests.Quest;
import com.web.rpg.model.abilities.Magic;
import com.web.rpg.model.abilities.buffs.BuffMagic;
import com.web.rpg.model.cities.City;
import com.web.rpg.service.world.Event;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayerCharacterConverter extends BaseConverter {

    public PlayerCharacterEntity convertToEntity(PlayerCharacter character) {
        return new PlayerCharacterEntity(
                character.getId(),
                character.getPlayerId(),
                character.getName(),
                character.getAgility(),
                character.getIntelligence(),
                character.getPower(),
                character.getExperience(),
                character.getLevel(),
                character.getCharacterClass(),
                character.getHitPoints(),
                character.getMaxHitPoints(),
                character.getManaPoints(),
                character.getMaxManaPoints(),
                character.getBaseDamage(),
                character.getDefence(),
                serializeObjectToByteArray(character.getItems()),
                serializeObjectToByteArray(character.getMagic()),
                character.getMagicPoint(),
                character.getExpToNextLevel(),
                character.getGold(),
                serializeObjectToByteArray(character.getBuffMagic()),
                character.getAdditionPower(),
                character.getAdditionIntelligence(),
                character.getAdditionAgility(),
                character.getCountOfBigHitPointBottle(),
                character.getCountOfMiddleHitPointBottle(),
                character.getCountOfSmallHitPointBottle(),
                character.getCountOfBigManaPointBottles(),
                character.getCountOfMiddleManaPointBottles(),
                character.getCountOfSmallManaPointBottles(),
                serializeObjectToByteArray(character.getQuest()),
                character.getCurrentAction(),
                character.getActionType().name(),
                character.getCountToEndOfAction(),
                serializeObjectToByteArray(character.getMonster()),
                serializeObjectToByteArray(character.getStory()),
                serializeObjectToByteArray(character.getCurrentCity()),
                serializeObjectToByteArray(character.getTargetCity()),
                character.getStepsToCity()
        );
    }

    public PlayerCharacter convertFromEntity(PlayerCharacterEntity characterEntity) {
        return new PlayerCharacter(
                characterEntity.getId(),
                characterEntity.getPlayerId(),
                characterEntity.getName(),
                characterEntity.getAgility(),
                characterEntity.getIntelligence(),
                characterEntity.getPower(),
                characterEntity.getExperience(),
                characterEntity.getLevel(),
                characterEntity.getCharacterClass(),
                characterEntity.getHitPoints(),
                characterEntity.getMaxHitPoints(),
                characterEntity.getManaPoints(),
                characterEntity.getMaxManaPoints(),
                characterEntity.getBaseDamage(),
                characterEntity.getDefence(),
                (List<Item>) deserializeObjectFromByte(characterEntity.getItems()),
                (Magic) deserializeObjectFromByte(characterEntity.getMagic()),
                characterEntity.getMagicPoint(),
                characterEntity.getExpToNextLevel(),
                characterEntity.getGold(),
                (BuffMagic) deserializeObjectFromByte(characterEntity.getBuffMagic()),
                characterEntity.getAdditionPower(),
                characterEntity.getAdditionIntelligence(),
                characterEntity.getAdditionAgility(),
                characterEntity.getCountOfBigHitPointBottle(),
                characterEntity.getCountOfMiddleHitPointBottle(),
                characterEntity.getCountOfSmallHitPointBottle(),
                characterEntity.getCountOfBigManaPointBottles(),
                characterEntity.getCountOfMiddleManaPointBottles(),
                characterEntity.getCountOfSmallManaPointBottles(),
                (Quest)deserializeObjectFromByte(characterEntity.getQuest()),
                characterEntity.getCurrentAction(),
                Event.valueOf(characterEntity.getActionType()),
                characterEntity.getCountToEndOfAction(),
                (Monster)deserializeObjectFromByte(characterEntity.getMonster()),
                (Story) deserializeObjectFromByte(characterEntity.getStory()),
                (City) deserializeObjectFromByte(characterEntity.getCurrentCity()),
                (City) deserializeObjectFromByte(characterEntity.getTargetCity()),
                characterEntity.getStepsToCity()
        );
    }
}
