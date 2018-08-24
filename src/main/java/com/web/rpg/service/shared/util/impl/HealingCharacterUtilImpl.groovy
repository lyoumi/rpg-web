package com.web.rpg.service.shared.util.impl;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.impl.heal.healHitPoint.items.BigHPBottle;
import com.web.rpg.model.Items.impl.heal.healHitPoint.items.MiddleHPBottle;
import com.web.rpg.model.Items.impl.heal.healHitPoint.items.SmallHPBottle;
import com.web.rpg.model.Items.impl.heal.healManaPoint.items.BigManaPointBottle;
import com.web.rpg.model.Items.impl.heal.healManaPoint.items.MiddleManaPointBottle;
import com.web.rpg.model.Items.impl.heal.healManaPoint.items.SmallManaPointBottle;
import com.web.rpg.service.shared.util.HealingCharacterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class HealingCharacterUtilImpl implements HealingCharacterUtil {

    private final bigHPBottle
    private final middleHPBottle
    private final smallHPBottle
    private final bigManaPointBottle
    private final middleManaPointBottle
    private final smallManaPointBottle

    @Autowired
    HealingCharacterUtilImpl(BigHPBottle bigHPBottle,
                             MiddleHPBottle middleHPBottle,
                             SmallHPBottle smallHPBottle,
                             BigManaPointBottle bigManaPointBottle,
                             MiddleManaPointBottle middleManaPointBottle,
                             SmallManaPointBottle smallManaPointBottle) {
        this.bigHPBottle = bigHPBottle
        this.middleHPBottle = middleHPBottle
        this.smallHPBottle = smallHPBottle
        this.bigManaPointBottle = bigManaPointBottle
        this.middleManaPointBottle = middleManaPointBottle
        this.smallManaPointBottle = smallManaPointBottle
    }

    @Override
    boolean autoHealHitPoints(PlayerCharacter character) {
        if (character.countOfBigHitPointBottle > 0) {
            bigHPBottle.use character
            character.countOfBigHitPointBottle = character.countOfBigHitPointBottle - 1
            true
        }
        else if (character.getCountOfMiddleHitPointBottle() > 0) {
            middleHPBottle.use character
            character.countOfMiddleHitPointBottle = character.countOfMiddleHitPointBottle - 1
            true
        }
        else if (character.countOfSmallHitPointBottle > 0) {
            smallHPBottle.use character
            character.countOfSmallHitPointBottle = character.countOfSmallHitPointBottle - 1
            true
        } else false
    }

    @Override
    boolean autoHealManaPoints(PlayerCharacter character) {
        if (character.countOfBigManaPointBottles > 0) {
            character.countOfBigManaPointBottles = character.countOfBigManaPointBottles - 1
            bigManaPointBottle.use character
            true
        } else if (character.countOfMiddleManaPointBottles > 0) {
            character.countOfMiddleManaPointBottles = character.countOfMiddleManaPointBottles - 1
            middleManaPointBottle.use character
            true
        } else if (character.countOfSmallManaPointBottles > 0) {
            character.countOfSmallManaPointBottles = character.countOfSmallManaPointBottles - 1
            smallManaPointBottle.use character
            true
        }
        else false
    }
}
