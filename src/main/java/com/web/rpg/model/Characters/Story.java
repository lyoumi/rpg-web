package com.web.rpg.model.Characters;

import lombok.Data;

import java.io.Serializable;

@Data
public class Story implements Serializable {
    private String prefix;
    private String currentAction;
    private Integer step;
    private boolean isEnd;
}
