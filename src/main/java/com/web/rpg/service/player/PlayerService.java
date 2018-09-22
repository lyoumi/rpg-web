package com.web.rpg.service.player;

import java.util.UUID;

public interface PlayerService {

    Integer getCountOfManaBottles(UUID id);
    Integer getCountOfHitPointBottles(UUID id);
}
