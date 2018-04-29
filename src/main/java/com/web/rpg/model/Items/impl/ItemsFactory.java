package com.web.rpg.model.Items.impl;

import com.web.rpg.model.Characters.PlayerCharacter;

public interface ItemsFactory {
    Item createNewItem(PlayerCharacter character);
}
