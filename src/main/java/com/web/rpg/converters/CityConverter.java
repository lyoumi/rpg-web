package com.web.rpg.converters;

import com.web.rpg.entity.CityEntity;
import com.web.rpg.model.cities.City;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CityConverter extends BaseConverter{

    public CityEntity convertToEntity(City city) {
        return new CityEntity(
                city.getId(),
                city.getName(),
                serializeObjectToByteArray(city.getCitiesNear())
        );
    }

    public City convertFromEntity(CityEntity cityEntity) {
        return new City(
                cityEntity.getId(),
                cityEntity.getName(),
                (Map<City, Integer>) deserializeObjectFromByte(cityEntity.getCitiesNear())
        );
    }


}
