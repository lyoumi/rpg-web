package com.web.rpg.repository;

import com.web.rpg.converters.CityConverter;
import com.web.rpg.dao.CityDao;
import com.web.rpg.entity.CityEntity;
import com.web.rpg.model.cities.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityRepository {

    private final CityDao cityDao;
    private final CityConverter converter;

    @Autowired
    public CityRepository(CityDao cityDao, CityConverter converter) {
        this.cityDao = cityDao;
        this.converter = converter;
    }

    public City save(City city) {
        return converter.convertFromEntity(cityDao.save(converter.convertToEntity(city)));
    }

    public City findByName(String name) {
        return converter.convertFromEntity(cityDao.findCityEntityByName(name));
    }

    public List<City> findAll() {
        List<CityEntity> cities = (List<CityEntity>) cityDao.findAll();
        return cities
                .stream()
                .map(converter::convertFromEntity)
                .collect(Collectors.toList());
    }
}
