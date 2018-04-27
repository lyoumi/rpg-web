package com.web.rpg.model.Monsters.service;

import com.web.rpg.model.Characters.CharacterClass;
import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Monsters.Monster;
import com.web.rpg.model.Monsters.MonsterNames;
import com.web.rpg.model.Monsters.monstersclasses.Boss;
import com.web.rpg.model.Monsters.monstersclasses.MediumBot;
import com.web.rpg.repository.MonsterRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MonsterServiceImpl implements MosterService {

    @Autowired
    private MonsterRepository monsterRepository;

    private static final Random RANDOM = new Random();
    private static final List<MonsterNames> MONSTER_NAMES = Collections.unmodifiableList(Arrays.asList(MonsterNames.values()));

    @Override
    public Monster prepearMonsterForBattle(PlayerCharacter character){
        MediumBot monster = new MediumBot();

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
        monsterRepository.save(monster);
        return monster;
    }

    @Override
    public boolean isDead(Monster monster) {
        if (monster instanceof MediumBot) {
            return ((MediumBot)monster).getHitPoint() > 0;
        } else {
            return ((Boss)monster).getHitPoint() > 0;
        }
    }

    @Override
    public void remove(MediumBot monster) {
        monsterRepository.delete(monster);
    }

    @Override
    public Monster updateOrCreate(Monster monster) {
        return monsterRepository.save(monster);
    }
}
