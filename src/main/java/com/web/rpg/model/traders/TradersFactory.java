package com.web.rpg.model.traders;

import com.web.rpg.model.Characters.PlayerCharacter;

public interface TradersFactory {
    Trader getTrader(PlayerCharacter character);
}
