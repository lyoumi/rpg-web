package com.web.rpg.controller;

import com.web.rpg.model.Characters.CharacterClass;
import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Quests.Quest;
import com.web.rpg.model.abilities.buffs.buffsclasses.ArchersBuff;
import com.web.rpg.model.abilities.buffs.buffsclasses.ForceOfJedi;
import com.web.rpg.service.character.CharacterService;
import com.web.rpg.service.world.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping(value = "game")
public class BasicController {


    private final CharacterService characterService;

    @Autowired
    public BasicController(CharacterService characterService) {
        this.characterService = characterService;
    }

    private static final Random RANDOM = new Random();

    @PostMapping(path = "create")
    public void create() {
        IntStream.range(0, 10)
                .forEach(value -> generator());
    }

    @GetMapping(path = "show")
    @ResponseBody
    public List<PlayerCharacter> show() {
        return characterService.findAll();
    }

    @PostMapping(path = "delete")
    public void delete() {
        characterService.deleteAll();
    }

    public void generator() {
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
        character.setBaseDamage(300000D);
        character.setDefence(30000);
        character.setItems(new ArrayList<>());
        character.setMagic(new ForceOfJedi(80));
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
        character.setStories(null);
        characterService.save(character);
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
