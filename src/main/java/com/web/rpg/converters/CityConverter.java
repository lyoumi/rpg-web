package com.web.rpg.converters;

import com.web.rpg.entity.CityEntity;
import com.web.rpg.model.cities.City;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

@Component
public class CityConverter {

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

    private byte[] serializeObjectToByteArray(Object o) {
        if (o != null) {
            return SerializationUtils.serialize((Serializable) o);
        }
        return null;
    }

    private Object deserializeObjectFromByte(byte[] byteArray) {
        if (byteArray != null) {
            return SerializationUtils.deserialize(byteArray);
        }
        return null;
    }
}
