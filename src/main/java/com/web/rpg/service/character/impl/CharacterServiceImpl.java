package com.web.rpg.service.character.impl;

import com.web.rpg.model.Characters.CharacterClass;
import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.EquipmentSlot;
import com.web.rpg.model.Items.impl.Item;
import com.web.rpg.model.Quests.Quest;
import com.web.rpg.model.abilities.buffs.buffsclasses.ArchersBuff;
import com.web.rpg.model.abilities.instants.instantmagics.combat.FireBall;
import com.web.rpg.repository.CharacterRepository;
import com.web.rpg.service.character.CharacterService;
import com.web.rpg.service.shared.util.HealingCharacterUtil;
import com.web.rpg.service.world.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CharacterServiceImpl implements CharacterService {

    private static final Random RANDOM = new Random();

    private final CharacterRepository characterRepository;
    private final HealingCharacterUtil healingCharacterUtil;

    @Autowired
    public CharacterServiceImpl(@Qualifier(value = "characterRepository") CharacterRepository characterRepository,
                                HealingCharacterUtil healingCharacterUtil) {
        this.characterRepository = characterRepository;
        this.healingCharacterUtil = healingCharacterUtil;
    }

    @Override
    public void processCharacters() {

    }

    @Override
    public Integer getCountOfManaBottles(UUID id) {
        if (id == null) {
            return null;
        }
        PlayerCharacter character = characterRepository.findOne(id);
        return character.getCountOfBigManaPointBottles() +
                character.getCountOfMiddleManaPointBottles() +
                character.getCountOfSmallManaPointBottles();
    }

    @Override
    public Integer getCountOfHitPointBottles(UUID id) {
        if (id == null) {
            return null;
        }
        PlayerCharacter character = characterRepository.findOne(id);
        return character.getCountOfBigHitPointBottle() +
                character.getCountOfMiddleHitPointBottle() +
                character.getCountOfSmallHitPointBottle();
    }

    @Override
    public PlayerCharacter createOrUpdateCharacter(PlayerCharacter character) {
        return characterRepository.save(character);
    }

    @Override
    public List<PlayerCharacter> findAll() {
        return characterRepository.findAll();
    }

    @Override
    public PlayerCharacter save(PlayerCharacter character) {
        return characterRepository.save(character);
    }

    @Override
    public PlayerCharacter findById(UUID id) {
        return characterRepository.findOne(id);
    }

    @Override
    public PlayerCharacter findByPlayerId(UUID id) {
        return characterRepository.getCharacterByPlayerId(id);
    }

    @Override
    public void deleteAll() {
        characterRepository.deleteAll();
    }

    @Override
    public void healCharacterHitPoints(PlayerCharacter character) {
        boolean isHealed = healingCharacterUtil.autoHealHitPoints(character);
        if (isHealed) {
            characterRepository.save(character);
        }
    }

    @Override
    public UUID prepeareCharacter() {
        return generateCharacter().getId();
    }

    @Override
    public void healCharacterManaPoints(PlayerCharacter character) {
        boolean isHealed = healingCharacterUtil.autoHealManaPoints(character);
        if (isHealed) {
            characterRepository.save(character);
        }
    }

    private Map<EquipmentSlot, Item> getCharacterEquipment(UUID id) {
        if (id == null) {
            return null;
        }
        return new HashMap<>();
    }

    public PlayerCharacter generateCharacter() {
        PlayerCharacter character = new PlayerCharacter();
        character.setId(UUID.randomUUID());
        character.setPlayerId(UUID.randomUUID());
        character.setName(generateName());
        character.setAgility(30000);
        character.setIntelligence(30000);
        character.setPower(30000);
        character.setExperience(30000L);
        character.setLevel(80);
        character.setCharacterClass(CharacterClass.BERSERK);
        character.setHitPoints(300000D);
        character.setMaxHitPoints(3000000D);
        character.setManaPoints(300000D);
        character.setMaxManaPoints(3000000D);
        character.setBaseDamage(1000D);
        character.setDefence(30);
        character.setItems(new ArrayList<>());
        character.setMagic(new FireBall(80));
        character.setMagicPoint(0);
        character.setExpToNextLevel(300000000);
        character.setGold(200000);
        character.setBuffMagic(new ArchersBuff(80));
        character.setAdditionAgility(2000);
        character.setAdditionIntelligence(20000);
        character.setAdditionPower(2000);
        character.setCountOfBigHitPointBottle(2000);
        character.setCountOfMiddleHitPointBottle(2000);
        character.setCountOfSmallHitPointBottle(2000);
        character.setCountOfBigManaPointBottles(2000);
        character.setCountOfMiddleManaPointBottles(2000);
        character.setCountOfSmallManaPointBottles(2000);
        character.setQuest(new Quest());
        character.setCurrentAction(Event.STORY.name());
        character.setActionType(Event.STORY);
        character.setCountToEndOfAction(0);
        character.setMonster(null);
        character.setStory(null);
        return save(character);
    }

    private String generateName() {
        Properties prop = new Properties();
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream("properties/character-names.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> characterNames = prop.entrySet()
                .stream()
                .map(entry -> (String) entry.getValue())
                .collect(Collectors.toList());
        return characterNames.get(RANDOM.nextInt(characterNames.size()));
    }
}
