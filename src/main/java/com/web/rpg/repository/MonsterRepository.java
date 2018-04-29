package com.web.rpg.repository;

import com.web.rpg.converters.MonsterConverter;
import com.web.rpg.dao.MonsterDao;
import com.web.rpg.model.Monsters.Monster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MonsterRepository {

    private final MonsterDao monsterDao;
    private final MonsterConverter converter;

    @Autowired
    public MonsterRepository(MonsterDao monsterDao, MonsterConverter converter) {
        this.monsterDao = monsterDao;
        this.converter = converter;
    }

    public void save(Monster monster) {
        monsterDao.save(converter.convertToEntity(monster));
    }

    public Monster findOne(UUID monsterId) {
        return converter.convertFromEntity(monsterDao.findOne(monsterId));
    }

    public void delete(Monster monster) {
        monsterDao.delete(converter.convertToEntity(monster));
    }
}
