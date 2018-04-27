package com.web.rpg.repository;

import com.web.rpg.model.Quests.Quest;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface QuestRepository extends CrudRepository<Quest, UUID> {
}
