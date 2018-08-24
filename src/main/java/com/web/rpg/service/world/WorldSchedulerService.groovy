package com.web.rpg.service.world;

import org.springframework.stereotype.Component;

@Component
interface WorldSchedulerService {
    void changeWorldStatement();

    void changeCharactersStatements();
}
