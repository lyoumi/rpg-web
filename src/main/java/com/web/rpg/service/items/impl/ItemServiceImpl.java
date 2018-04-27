package com.web.rpg.service.items.impl;

import com.web.rpg.model.Items.itemsclasses.Item;
import com.web.rpg.repository.ItemRepository;
import com.web.rpg.service.items.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item findById(UUID id) {
        return itemRepository.findOne(id);
    }

    @Override
    public Item updateOrCreate(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void removeItem(Item item) {
        itemRepository.delete(item);
    }

    @Override
    public void removeItemById(UUID id) {
        itemRepository.delete(id);
    }
}
