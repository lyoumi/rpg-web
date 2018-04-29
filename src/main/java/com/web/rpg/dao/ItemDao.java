package com.web.rpg.dao;

import com.web.rpg.model.Items.impl.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ItemDao extends CrudRepository<Item, UUID> {
}
