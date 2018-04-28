package com.web.rpg.model.Characters;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@Document
public class Stories {
    @Id
    String id;
    String story;
}
