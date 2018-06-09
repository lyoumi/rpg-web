package com.web.rpg.model.abilities.strategies;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.abilities.MagicClasses;
import org.springframework.stereotype.Component;

@Component
public class BuffMagicStrategy extends AbstractDefaultMagicStrategy {
    @Override
    public void useMagic(PlayerCharacter character) {

    }

    @Override
    public MagicClasses getMagicClass() {
        return MagicClasses.BUFF;
    }
}
