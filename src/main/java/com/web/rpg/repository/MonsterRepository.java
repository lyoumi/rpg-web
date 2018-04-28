package com.web.rpg.repository;

import com.web.rpg.model.Monsters.monstersclasses.Monster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class MonsterRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(Monster entity) {
        mongoTemplate.save(entity);
    }

    public Monster findOne(UUID uuid) {
        return mongoTemplate.findOne(
                Query.query(Criteria.where("name").is("Jack")), Monster.class);
    }

//    public boolean exists(UUID uuid) {
//        return false;
//    }

    public Iterable<Monster> findAll() {
        return mongoTemplate.findAll(Monster.class);
    }

//    public Iterable<Monster> findAll(Iterable<UUID> uuids) {
//        return null;
//    }

    public long count() {
        return 0;
    }

    public void delete(Monster entity) {
        mongoTemplate.remove(entity, "monster");
    }
}
