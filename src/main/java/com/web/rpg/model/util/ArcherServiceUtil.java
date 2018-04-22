package com.web.rpg.model.util;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class ArcherServiceUtil {

    @Getter
    private final double multiplierAgility = 5;
    @Getter
    private final double multiplierIntelligence = 4;
    @Getter
    private final double multiplierPower = 3;
}
