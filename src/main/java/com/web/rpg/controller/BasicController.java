package com.web.rpg.controller;

import com.web.rpg.model.Characters.CharacterClass;
import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Quests.Quest;
import com.web.rpg.model.abilities.buffs.buffsclasses.ArchersBuff;
import com.web.rpg.model.abilities.buffs.buffsclasses.ForceOfJedi;
import com.web.rpg.service.character.CharacterService;
import com.web.rpg.service.cities.CityService;
import com.web.rpg.service.world.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final CityService cityService;

    @Autowired
    public BasicController(CharacterService characterService, CityService cityService) {
        this.characterService = characterService;
        this.cityService = cityService;
    }

    private static final Random RANDOM = new Random();

    @PostMapping(path = "create")
    public void create() {
        IntStream.range(0, 10)
                .forEach(value -> generator(generateName()));
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

//    @RequestMapping(path = "create/my")
//    public void createMy(@ModelAttribute PlayerCharacter character) {
//        characterService.save(character);
//    }

    @GetMapping(path = "main")
    public String getMainPage() {
        System.out.println("You are here");
        return "index";
    }

    @GetMapping(path = "add")
    public String add(Model model) {
        model.addAttribute("character", new PlayerCharacter());
        return "character-add";
    }

    @PostMapping(path = "add")
    public String add(PlayerCharacter character,
                      BindingResult bindingResult) {
        PlayerCharacter playerCharacter = generator(character.getName());
        return playerCharacter.getId().toString();
    }

    @GetMapping(path = "{id}")
    public String showMyCharacter(Model model, @PathVariable("id") UUID id) {
        PlayerCharacter character = characterService.findById(id);
        model.addAttribute("character", character);
        return "character-show";
    }


//    @PostMapping(path = "city")
//    public void generateWorld() {
//        City city18 = new City(UUID.randomUUID(), "City18", null);
//        City noWonderLand = new City(UUID.randomUUID(), "No Wonder Land", null);
//        City knocksmidt = new City(UUID.randomUUID(), "Knocksmidt",null);
//        City unnamed = new City(UUID.randomUUID(), "Unnamed", null);
//        City city42 = new City(UUID.randomUUID(), "City 42", null);
//        City blackDoor = new City(UUID.randomUUID(), "Black Door", null);
//        City moonCity = new City(UUID.randomUUID(), "Moon City", null);
//        City sherwood = new City(UUID.randomUUID(), "Sherwood", null);
//        City yustrown = new City(UUID.randomUUID(), "Yustrown", null);
//        City riverran = new City(UUID.randomUUID(), "Riverran", null);
//        City silentHAven = new City(UUID.randomUUID(), "Silent Haven", null);
//        City castleRock = new City(UUID.randomUUID(), "Castle Rock", null);
//        City winterfell = new City(UUID.randomUUID(), "Winterfell", null);
//        City sunnyCity = new City(UUID.randomUUID(), "Sunny currentCity", null);
//
//        sunnyCity.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(castleRock, 3).put( winterfell, 5).build()));
//        castleRock.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(riverran, 4).put(yustrown, 7).put(moonCity, 50).put(sunnyCity, 3).build()));
//        winterfell.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(silentHAven, 10).put(sherwood, 5).put(sunnyCity, 5).build()));
//        silentHAven.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(yustrown, 5).put(winterfell, 10).build()));
//        yustrown.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(castleRock, 7).put(silentHAven, 5).build()));
//        riverran.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(blackDoor, 5).put(sherwood, 8).put(city42, 6).put(unnamed, 7).put(castleRock, 4).build()));
//        sherwood.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(knocksmidt, 5).put(city18, 6).put(winterfell, 5).put(riverran, 8).build()));
//        moonCity.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(castleRock, 50).build()));
//        blackDoor.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(riverran, 5).build()));
//        city42.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(riverran, 8).build()));
//        unnamed.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(riverran, 7).build()));
//        knocksmidt.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(sherwood, 5).build()));
//        noWonderLand.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(city18, 10).build()));
//        city18.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(sherwood, 6).put(noWonderLand, 10).build()));
//
//        cityService.save(city18);
//        cityService.save(city42);
//        cityService.save(moonCity);
//        cityService.save(sunnyCity);
//        cityService.save(blackDoor);
//        cityService.save(castleRock);
//        cityService.save(knocksmidt);
//        cityService.save(noWonderLand);
//        cityService.save(riverran);
//        cityService.save(sherwood);
//        cityService.save(silentHAven);
//        cityService.save(unnamed);
//        cityService.save(winterfell);
//        cityService.save(yustrown);
//    }

//    private Map<City, Integer> getMap() {
//        Map<City, Integer> map = new HashMap<>();
//
//    }

    public PlayerCharacter generator(String name) {
        PlayerCharacter character = new PlayerCharacter();
        character.setId(UUID.randomUUID());
        character.setPlayerId(UUID.randomUUID());
        character.setName(name);
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
        return characterService.save(character);
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
