package com.web.rpg.service.player;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface PlayerService {

    Integer getCountOfManaBottles(UUID id);
    Integer getCountOfHitPointBottles(UUID id);
}
