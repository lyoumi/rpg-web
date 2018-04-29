package com.web.rpg.model.cities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class City implements Serializable {

    private UUID id;
    private String name;
    private Map<City, Integer> citiesNear;
}
