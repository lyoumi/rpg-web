package com.web.rpg.service.world;

import com.web.rpg.service.world.interfaces.WorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class WorldScheduler {

    @Autowired
    private WorldService worldService;

    @Scheduled(fixedDelay = 5000)
    public void scheduleFixedDelayTask(){
        worldService.changeWorldStatement();
    }
}
