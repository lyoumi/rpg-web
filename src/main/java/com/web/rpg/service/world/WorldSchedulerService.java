package com.web.rpg.service.world;

import org.springframework.stereotype.Component;

@Component
public interface WorldSchedulerService {
    void changeWorldStatement();

    void changeCharactersStatements();
}
