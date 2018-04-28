package com.web.rpg.model.Monsters.service;

import com.web.rpg.model.Characters.CharacterClass;
import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Monsters.MonsterNames;
import com.web.rpg.model.Monsters.monstersclasses.Monster;
import com.web.rpg.repository.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class MonsterServiceImpl implements MonsterService {

    @Autowired
    private MonsterRepository monsterRepository;

    private static final Random RANDOM = new Random();
    private static final List<MonsterNames> MONSTER_NAMES = Collections.unmodifiableList(Arrays.asList(MonsterNames.values()));

    @Override
    public Monster prepearMonsterForBattle(PlayerCharacter character){
        Monster monster = new Monster();
        monster.setMonsterId(UUID.randomUUID());
        if (CharacterClass.BERSERK.equals(character.getCharacterClass())) {
            monster.setLevel(character.getLevel() + 4);
        } else {
            monster.setLevel(character.getLevel() + 1);
        }
        if (character.getLevel() > 5){
            monster.setExperience(character.getLevel() * 50);
            monster.setHitPoint(monster.getLevel() * 150);
            monster.setDamage(monster.getLevel() * 40);
        }else {
            monster.setExperience(character.getLevel()*10);
            monster.setHitPoint(monster.getLevel() * 70);
            monster.setDamage(monster.getLevel() * 20);
        }
        monster.setName(MONSTER_NAMES.get(RANDOM.nextInt(MONSTER_NAMES.size())).toString());
        return monster;
    }

    @Override
    public void remove(Monster monster) {
        monsterRepository.delete(monster);
    }

    @Override
    public Monster updateOrCreate(Monster monster) {
        monsterRepository.save(monster);
        return monsterRepository.findOne(((Monster)monster).getMonsterId());
    }
}
