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
public class HealingCharacterUtilImpl implements HealingCharacterUtil {

    private final BigHPBottle bigHPBottle;
    private final MiddleHPBottle middleHPBottle;
    private final SmallHPBottle smallHPBottle;
    private final BigManaPointBottle bigManaPointBottle;
    private final MiddleManaPointBottle middleManaPointBottle;
    private final SmallManaPointBottle smallManaPointBottle;

    @Autowired
    public HealingCharacterUtilImpl(BigHPBottle bigHPBottle,
                                    MiddleHPBottle middleHPBottle,
                                    SmallHPBottle smallHPBottle,
                                    BigManaPointBottle bigManaPointBottle,
                                    MiddleManaPointBottle middleManaPointBottle,
                                    SmallManaPointBottle smallManaPointBottle) {
        this.bigHPBottle = bigHPBottle;
        this.middleHPBottle = middleHPBottle;
        this.smallHPBottle = smallHPBottle;
        this.bigManaPointBottle = bigManaPointBottle;
        this.middleManaPointBottle = middleManaPointBottle;
        this.smallManaPointBottle = smallManaPointBottle;
    }

    @Override
    public boolean autoHealHitPoints(PlayerCharacter character) {
        if (character.getCountOfBigHitPointBottle() > 0) {
            bigHPBottle.use(character);
            character.setCountOfBigHitPointBottle(character.getCountOfBigHitPointBottle() - 1);
            return true;
        }
        else if (character.getCountOfMiddleHitPointBottle() > 0) {
            middleHPBottle.use(character);
            character.setCountOfMiddleHitPointBottle(character.getCountOfMiddleHitPointBottle() - 1);
            return true;
        }
        else if (character.getCountOfSmallHitPointBottle() > 0) {
            smallHPBottle.use(character);
            character.setCountOfSmallHitPointBottle(character.getCountOfSmallHitPointBottle() - 1);
            return true;
        } else return false;
    }

    @Override
    public boolean autoHealManaPoints(PlayerCharacter character) {
        if (character.getCountOfBigManaPointBottles() > 0) {
            character.setCountOfBigManaPointBottles(character.getCountOfBigManaPointBottles() - 1);
            bigManaPointBottle.use(character);
            return true;
        }
        else if (character.getCountOfMiddleManaPointBottles() > 0) {
            character.setCountOfMiddleManaPointBottles(character.getCountOfMiddleManaPointBottles() - 1);
            middleManaPointBottle.use(character);
            return true;
        }
        else if (character.getCountOfSmallManaPointBottles() > 0) {
            character.setCountOfSmallManaPointBottles(character.getCountOfSmallManaPointBottles() - 1);
            smallManaPointBottle.use(character);
            return true;
        }
        else return false;
    }
}
