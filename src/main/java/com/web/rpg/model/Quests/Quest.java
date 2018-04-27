package com.web.rpg.model.Quests;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.UUID;

@Data
@Document
public class Quest implements Serializable {
    @Id
    private UUID id;
    private Double exp;
    private Integer gold;
}
