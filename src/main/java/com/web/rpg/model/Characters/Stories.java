package com.web.rpg.model.Characters;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Stories {
    @Id
    String id;
    String story;
}
