package com.web.rpg.service.character.impl;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.EquipmentSlot;
import com.web.rpg.model.Items.impl.Item;
import com.web.rpg.repository.CharacterRepository;
import com.web.rpg.service.character.CharacterService;
import com.web.rpg.service.shared.util.HealingCharacterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;
    private final HealingCharacterUtil healingCharacterUtil;

    @Autowired
    public CharacterServiceImpl(@Qualifier(value = "characterRepository") CharacterRepository characterRepository,
                                HealingCharacterUtil healingCharacterUtil) {
        this.characterRepository = characterRepository;
        this.healingCharacterUtil = healingCharacterUtil;
    }

    @Override
    public void processCharacters() {

    }

    @Override
    public Integer getCountOfManaBootles(UUID id) {
        if (id == null) {
            return null;
        }
        PlayerCharacter character = characterRepository.findOne(id);
        return character.getCountOfBigManaPointBottles() +
                character.getCountOfMiddleManaPointBottles() +
                character.getCountOfSmallManaPointBottles();
    }

    @Override
    public Integer getCountOfHitPointBootles(UUID id) {
        if (id == null) {
            return null;
        }
        PlayerCharacter character = characterRepository.findOne(id);
        return character.getCountOfBigHitPointBottle() +
                character.getCountOfMiddleHitPointBottle() +
                character.getCountOfSmallHitPointBottle();
    }

    @Override
    public PlayerCharacter createOrUpdateCharacter(PlayerCharacter character) {
        return characterRepository.save(character);
    }

    @Override
    public List<PlayerCharacter> findAll() {
        return characterRepository.findAll();
    }

    @Override
    public PlayerCharacter save(PlayerCharacter character) {
        return characterRepository.save(character);
    }

    @Override
    public PlayerCharacter findById(UUID id) {
        return characterRepository.findOne(id);
    }

    @Override
    public PlayerCharacter findByPlayerId(UUID id) {
        return characterRepository.getCharacterByPlayerId(id);
    }

    @Override
    public void deleteAll() {
        characterRepository.deleteAll();
    }

    @Override
    public void healCharacterHitPoints(PlayerCharacter character) {
        boolean isHealed = healingCharacterUtil.autoHealHitPoints(character);
        if (isHealed) {
            characterRepository.save(character);
        }
    }

    @Override
    public void healCharacterManaPoints(PlayerCharacter character) {
        boolean isHealed = healingCharacterUtil.autoHealManaPoints(character);
        if (isHealed) {
            characterRepository.save(character);
        }
    }

    private Map<EquipmentSlot, Item> getCharacterEquipment(UUID id) {
        if (id == null) {
            return null;
        }
        return new HashMap<>();
    }
}
