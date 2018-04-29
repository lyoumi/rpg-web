package com.web.rpg.service.items.impl;

import com.web.rpg.model.Items.impl.Item;
import com.web.rpg.dao.ItemDao;
import com.web.rpg.service.items.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public Item findById(UUID id) {
        return itemDao.findOne(id);
    }

    @Override
    public Item updateOrCreate(Item item) {
        return itemDao.save(item);
    }

    @Override
    public void removeItem(Item item) {
        itemDao.delete(item);
    }

    @Override
    public void removeItemById(UUID id) {
        itemDao.delete(id);
    }
}
