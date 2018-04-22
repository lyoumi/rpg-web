package com.web.rpg.model.util;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class BerserkServiceUtil {

    @Getter
    private final double multiplierAgility = 4;
    @Getter
    private final double multiplierIntelligence = 3;
    @Getter
    private final double multiplierPower = 6;
}
