package com.web.rpg.model.accounts;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {
    private String name;
    private String surName;
    private String email;
}
