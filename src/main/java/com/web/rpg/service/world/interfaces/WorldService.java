package com.web.rpg.service.world.interfaces;

import org.springframework.stereotype.Component;

@Component
public interface WorldService {
    void changeWorldStatement();

    void changeCharactersStatements();
}
