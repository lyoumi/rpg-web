package com.web.rpg.service.cities;

import com.web.rpg.model.cities.City;

import java.util.List;

public interface CityService {
    City findByName(String name);

    City save(City city);

    List<City> findAll();
}
