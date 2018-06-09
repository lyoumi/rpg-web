package com.web.rpg.model.accounts;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class UserInfo implements Serializable {
    private String name;
    private String surName;
    private UUID characterId;
    private String email;

    @Override
    public String toString() {
        return "UserInfo" +
                "name: " + name + '\'' +
                " surName:'" + surName + '\'' +
                " email:'" + email;
    }
}
