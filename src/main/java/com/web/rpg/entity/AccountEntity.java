package com.web.rpg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class AccountEntity {

    @Id
    private UUID id;
    private String login;
    private byte[] password;
    private byte[] userInfo;
}
