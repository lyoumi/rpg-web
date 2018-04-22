package com.web.rpg.service.worldServices.interfaces;

import org.springframework.stereotype.Component;

@Component
public interface WorldService {
    void changeWorldStatement();

    void changeCharactersStatements();
}
