package com.web.rpg.model.Quests;

import lombok.Data;

import java.io.Serializable;

@Data
public class Quest implements Serializable {

    private String title;
    private Long exp;
    private Integer gold;
}
