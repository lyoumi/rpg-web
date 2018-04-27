package com.web.rpg.repository;

import com.web.rpg.model.Items.itemsclasses.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ItemRepository extends CrudRepository<Item, UUID> {
}
