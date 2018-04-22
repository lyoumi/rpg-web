package com.web.rpg.model.Items.itemsclasses;

import com.web.rpg.model.Characters.PlayerCharacter;

public interface ItemsFactory {
    Item createNewItem(PlayerCharacter character);
}
