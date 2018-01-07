package com.web.rpg.model.traders;

import com.web.rpg.model.Characters.Character;

public interface TradersFactory {
    Trader getTrader(Character character);
}
