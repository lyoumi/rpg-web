package com.web.rpg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class AccountEntity implements Serializable {

    @Id
    @Transient
    private UUID id = UUID.randomUUID();
    private String username;
    private String role;
    private byte[] password;
    private byte[] userInfo;
}
