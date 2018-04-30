package com.web.rpg.model.accounts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private UUID id;
    private String login;
    private String password;
    private UserInfo userInfo;
}
