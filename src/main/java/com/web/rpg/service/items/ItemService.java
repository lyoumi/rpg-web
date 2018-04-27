package com.web.rpg.service.items;

import com.web.rpg.model.Items.itemsclasses.Item;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ItemService {

    Item findById(UUID id);

    Item updateOrCreate(Item item);

    void removeItem(Item item);

    void removeItemById(UUID id);
}
