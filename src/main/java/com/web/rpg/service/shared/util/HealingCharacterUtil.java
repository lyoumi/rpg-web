package com.web.rpg.service.shared.util;

import com.web.rpg.model.Characters.PlayerCharacter;

public interface HealingCharacterUtil {
    boolean autoHealHitPoints(PlayerCharacter character);

    boolean autoHealManaPoints(PlayerCharacter character);
}
