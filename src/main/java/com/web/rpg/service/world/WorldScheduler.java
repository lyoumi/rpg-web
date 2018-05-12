package com.web.rpg.service.world;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class WorldScheduler {

    @Autowired
    private WorldSchedulerService worldSchedulerService;

    @Scheduled(fixedDelay = 5000)
    public void scheduleFixedDelayTask(){
        worldSchedulerService.changeWorldStatement();
    }
}
