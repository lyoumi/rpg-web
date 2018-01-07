package com.web.rpg.model.Items.itemsclasses;

import com.web.rpg.model.Characters.Character;

public interface ItemsFactory {
    Item createNewItem(Character character);
}
