package com.web.rpg.service.playerService;

import org.springframework.stereotype.Service;

@Service
public interface PlayerService {

    Integer getCountOfManaBottles(String id);
    Integer getCountOfHitPointBottles(String id);
}
