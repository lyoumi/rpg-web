package com.web.rpg.repository;

import com.web.rpg.converters.PlayerCharacterConverter;
import com.web.rpg.dao.CharacterDao;
import com.web.rpg.entity.PlayerCharacterEntity;
import com.web.rpg.model.Characters.PlayerCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CharacterRepository {

    private final CharacterDao characterDao;
    private final PlayerCharacterConverter converter;

    @Autowired
    public CharacterRepository(CharacterDao characterDao, PlayerCharacterConverter converter) {
        this.characterDao = characterDao;
        this.converter = converter;
    }


    public PlayerCharacter findOne(UUID id) {
        return converter.convertFromEntity(characterDao.findOne(id));
    }

    public PlayerCharacter save(PlayerCharacter character) {
        return converter.convertFromEntity(characterDao.save(converter.convertToEntity(character)));
    }

    public List<PlayerCharacter> findAll() {
        List<PlayerCharacterEntity> all = (List<PlayerCharacterEntity>) characterDao.findAll();
        return all
                .parallelStream()
                .map(playerCharacterEntity -> converter.convertFromEntity(playerCharacterEntity))
                .collect(Collectors.toList());
    }

    public PlayerCharacter getCharacterByPlayerId(UUID id) {
        return converter.convertFromEntity(characterDao.getCharacterByPlayerId(id));
    }

    public void deleteAll() {
        characterDao.deleteAll();
    }
}
