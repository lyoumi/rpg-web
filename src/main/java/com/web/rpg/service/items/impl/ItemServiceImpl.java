package com.web.rpg.service.items.impl;

import com.web.rpg.model.Items.impl.Item;
import com.web.rpg.repository.ItemRepository;
import com.web.rpg.service.items.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
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
