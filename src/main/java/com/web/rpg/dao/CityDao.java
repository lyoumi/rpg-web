package com.web.rpg.dao;

import com.web.rpg.entity.CityEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CityDao extends CrudRepository<CityEntity, UUID> {
    CityEntity findCityEntityByName(String name);
}
