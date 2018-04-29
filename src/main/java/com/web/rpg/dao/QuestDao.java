package com.web.rpg.dao;

import com.web.rpg.model.Quests.Quest;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface QuestDao extends CrudRepository<Quest, UUID> {
}
