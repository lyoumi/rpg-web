package com.web.rpg.service.items;

import com.web.rpg.model.Items.impl.Item;

import java.util.UUID;

public interface ItemService {

    Item findById(UUID id);

    Item updateOrCreate(Item item);

    void removeItem(Item item);

    void removeItemById(UUID id);
}
