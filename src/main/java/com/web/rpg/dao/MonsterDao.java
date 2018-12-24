package com.web.rpg.dao;

import com.web.rpg.entity.MonsterEntity;
import com.web.rpg.model.Monsters.Monster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class MonsterDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(MonsterEntity entity) {
        mongoTemplate.save(entity);
    }

    public MonsterEntity findOne(UUID uuid) {
        return mongoTemplate.findOne(
                Query.query(Criteria.where("id").is(uuid)), MonsterEntity.class);
    }

    public Iterable<Monster> findAll() {
        return mongoTemplate.findAll(Monster.class);
    }

    public long count() {
        return 0;
    }

    public void delete(MonsterEntity entity) {
        mongoTemplate.remove(entity, "monsterEntity");
    }
}
