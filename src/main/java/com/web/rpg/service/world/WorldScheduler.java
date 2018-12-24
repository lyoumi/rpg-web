package com.web.rpg.service.world;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class WorldScheduler {

    private static final int TIME_TO_ACTION = 5000;

    private final WorldSchedulerService worldSchedulerService;

    @Autowired
    public WorldScheduler(WorldSchedulerService worldSchedulerService) {
        this.worldSchedulerService = worldSchedulerService;
    }

    @Scheduled(fixedDelay = TIME_TO_ACTION)
    public void scheduleFixedDelayTask(){
        worldSchedulerService.changeWorldStatement();
    }
}
