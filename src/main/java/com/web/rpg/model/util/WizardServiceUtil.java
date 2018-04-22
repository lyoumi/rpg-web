package com.web.rpg.model.util;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class WizardServiceUtil {

    @Getter
    private final double multiplierAgility = 6;
    @Getter
    private final double multiplierIntelligence = 3;
    @Getter
    private final double multiplierPower = 3;
}
