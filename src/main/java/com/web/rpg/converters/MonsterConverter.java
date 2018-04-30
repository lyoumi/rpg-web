package com.web.rpg.converters;

import com.web.rpg.entity.MonsterEntity;
import com.web.rpg.model.Monsters.Monster;
import org.springframework.stereotype.Component;

@Component
public class MonsterConverter {

    public MonsterEntity convertToEntity(Monster monster) {
        if (monster != null) {
            return new MonsterEntity(
                    monster.getMonsterId(),
                    monster.getLevel(),
                    monster.getDamage(),
                    monster.getHitPoint(),
                    monster.getExperience(),
                    monster.getGold(),
                    monster.getName()
            );
        } else {
            return null;
        }
    }

    public Monster convertFromEntity(MonsterEntity monster) {
        if (monster != null) {
            return new Monster(
                    monster.getId(),
                    monster.getLevel(),
                    monster.getDamage(),
                    monster.getHitPoint(),
                    monster.getExperience(),
                    monster.getGold(),
                    monster.getName()
            );
        } else {
            return null;
        }
    }
}
