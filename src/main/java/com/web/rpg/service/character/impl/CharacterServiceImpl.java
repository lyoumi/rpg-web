package com.web.rpg.service.character.impl;

import com.web.rpg.converters.PlayerCharacterConverter;
import com.web.rpg.entity.PlayerCharacterEntity;
import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.EquipmentSlot;
import com.web.rpg.model.Items.impl.Item;
import com.web.rpg.repository.CharacterRepository;
import com.web.rpg.service.character.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    @Qualifier(value = "characterRepository")
    private CharacterRepository characterRepository;
    @Autowired
    private PlayerCharacterConverter converter;

    @Override
    public void processCharacters() {

    }

    @Override
    public Integer getCountOfManaBootles(UUID id) {
        if (id == null) {
            return null;
        }
        PlayerCharacter character = converter.convertFromEntity(characterRepository.findOne(id));
        return character.getCountOfBigManaPointBottles() +
                character.getCountOfMiddleManaPointBottles() +
                character.getCountOfSmallManaPointBottles();
    }

    @Override
    public Integer getCountOfHitPointBootles(UUID id) {
        if (id == null) {
            return null;
        }
        PlayerCharacter character = converter.convertFromEntity(characterRepository.findOne(id));
        return character.getCountOfBigHitPointBottle() +
                character.getCountOfMiddleHitPointBottle() +
                character.getCountOfSmallHitPointBottle();
    }

    @Override
    public PlayerCharacter createOrUpdateCharacter(PlayerCharacter character) {
        return converter.convertFromEntity(characterRepository.save(converter.convertToEntity(character)));
    }

    @Override
    public List<PlayerCharacter> findAll() {
        List<PlayerCharacterEntity> allCharacters = (List<PlayerCharacterEntity>) characterRepository.findAll();
        return allCharacters
                .stream()
                .map(characterEntity -> converter.convertFromEntity(characterEntity))
                .collect(Collectors.toList());
    }

    @Override
    public PlayerCharacter save(PlayerCharacter character) {
        return converter.convertFromEntity(characterRepository.save(converter.convertToEntity(character)));
    }

    @Override
    public PlayerCharacter findById(UUID id) {
        return converter.convertFromEntity(characterRepository.findOne(id));
    }

    @Override
    public PlayerCharacter findByPlayerId(UUID id) {
        return converter.convertFromEntity(characterRepository.getCharacterByPlayerId(id));
    }

    @Override
    public void deleteAll() {
        characterRepository.deleteAll();
    }

    private Map<EquipmentSlot, Item> getCharacterEquipment(UUID id) {
        if (id == null) {
            return null;
        }
        return new HashMap<>();
    }
}
