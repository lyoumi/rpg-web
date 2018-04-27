package com.web.rpg.repository;

import com.web.rpg.model.Monsters.Monster;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MonsterRepository extends CrudRepository<Monster, UUID> {
}
