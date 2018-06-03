package com.web.rpg.model.accounts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Serializable {

    private UUID id;
    private String login;
    private String role;
    private String password;
    private UserInfo userInfo;
}
