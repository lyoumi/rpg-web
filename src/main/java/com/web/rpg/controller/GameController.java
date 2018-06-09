package com.web.rpg.controller;

import com.google.common.collect.ImmutableMap;
import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.abilities.MagicStrategyFactory;
import com.web.rpg.model.cities.City;
import com.web.rpg.service.accounts.AccountManagerService;
import com.web.rpg.service.character.CharacterService;
import com.web.rpg.service.cities.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Controller
@RequestMapping(value = EndpointInfo.GAME_URL)
public class GameController {

    private final CharacterService characterService;
    private final CityService cityService;
    private final AccountManagerService accountManagerService;
    private final MagicStrategyFactory magicStrategyFactory;

    @Autowired
    public GameController(CharacterService characterService,
                          CityService cityService,
                          AccountManagerService accountManagerService,
                          MagicStrategyFactory magicStrategyFactory) {
        this.characterService = characterService;
        this.cityService = cityService;
        this.accountManagerService = accountManagerService;
        this.magicStrategyFactory = magicStrategyFactory;
    }

    @GetMapping
    public String redirectToCharacterPage(Principal principal){
        UUID characterId = accountManagerService.getAccountByLogin(principal.getName()).getUserInfo().getCharacterId();
        return "redirect:/game/" + characterId.toString();
    }

    @GetMapping(path = "story")
    public String redirectToCharacterStoryPage(Principal principal) {
        UUID characterId = accountManagerService.getAccountByLogin(principal.getName()).getUserInfo().getCharacterId();
        return "redirect:/game/" + characterId.toString() + "/story";
    }

    @PostMapping(path = "create")
    public void create() {
        IntStream.range(0, 10000)
                .forEach(value -> characterService.prepeareCharacter());
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

    @GetMapping(path = "{id}")
    public String showMyCharacter(Model model, @PathVariable("id") UUID id) {
        PlayerCharacter character = characterService.findById(id);
        model.addAttribute("character", character);
        return "character-show";
    }

    @GetMapping(path = "{id}/get")
    @ResponseBody
    public ResponseEntity getCurrentCharacter(@PathVariable("id") UUID id) {
        PlayerCharacter character = characterService.findById(id);
        return new ResponseEntity(character, HttpStatus.OK);
    }

    @GetMapping(path = "{id}/story")
    public String getCurrentStory(Model model, @PathVariable("id") UUID id) {
        PlayerCharacter character = characterService.findById(id);
        model.addAttribute("character", character);
        return "character-show-story";
    }

    @PostMapping(path = "{id}/healhp")
    @ResponseBody
    public ResponseEntity healCharacterHP(@PathVariable("id") UUID id) {
        characterService.healCharacterHitPoints(characterService.findById(id));
        return new ResponseEntity(characterService.findById(id).getHitPoints().intValue(), HttpStatus.OK);
    }

    @PostMapping(path = "{id}/healmp")
    public ResponseEntity healCharacterMP(@PathVariable("id") UUID id) {
        characterService.healCharacterManaPoints(characterService.findById(id));
        return new ResponseEntity(characterService.findById(id).getManaPoints().intValue(), HttpStatus.OK);
    }

    @PostMapping(path = "{id}/skill")
    @ResponseBody
    public ResponseEntity useSkill(@PathVariable("id") UUID id) {
        PlayerCharacter playerCharacter = characterService.findById(id);
        if (playerCharacter != null && playerCharacter.getMonster() != null && playerCharacter.getMagic() != null) {
            magicStrategyFactory.useMagic(playerCharacter);
            return new ResponseEntity(playerCharacter, HttpStatus.OK);
        } else {
            return new ResponseEntity(playerCharacter, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }


//    @PostMapping(path = "city")
    public void generateWorld() {
        City city18 = new City(UUID.randomUUID(), "City18", null);
        City noWonderLand = new City(UUID.randomUUID(), "No Wonder Land", null);
        City knocksmidt = new City(UUID.randomUUID(), "Knocksmidt",null);
        City unnamed = new City(UUID.randomUUID(), "Unnamed", null);
        City city42 = new City(UUID.randomUUID(), "City 42", null);
        City blackDoor = new City(UUID.randomUUID(), "Black Door", null);
        City moonCity = new City(UUID.randomUUID(), "Moon City", null);
        City sherwood = new City(UUID.randomUUID(), "Sherwood", null);
        City yustrown = new City(UUID.randomUUID(), "Yustrown", null);
        City riverran = new City(UUID.randomUUID(), "Riverran", null);
        City silentHAven = new City(UUID.randomUUID(), "Silent Haven", null);
        City castleRock = new City(UUID.randomUUID(), "Castle Rock", null);
        City winterfell = new City(UUID.randomUUID(), "Winterfell", null);
        City sunnyCity = new City(UUID.randomUUID(), "Sunny currentCity", null);

        sunnyCity.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(castleRock, 3).put( winterfell, 5).build()));
        castleRock.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(riverran, 4).put(yustrown, 7).put(moonCity, 50).put(sunnyCity, 3).build()));
        winterfell.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(silentHAven, 10).put(sherwood, 5).put(sunnyCity, 5).build()));
        silentHAven.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(yustrown, 5).put(winterfell, 10).build()));
        yustrown.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(castleRock, 7).put(silentHAven, 5).build()));
        riverran.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(blackDoor, 5).put(sherwood, 8).put(city42, 6).put(unnamed, 7).put(castleRock, 4).build()));
        sherwood.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(knocksmidt, 5).put(city18, 6).put(winterfell, 5).put(riverran, 8).build()));
        moonCity.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(castleRock, 50).build()));
        blackDoor.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(riverran, 5).build()));
        city42.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(riverran, 8).build()));
        unnamed.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(riverran, 7).build()));
        knocksmidt.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(sherwood, 5).build()));
        noWonderLand.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(city18, 10).build()));
        city18.setCitiesNear(new HashMap(ImmutableMap.<City, Integer>builder().put(sherwood, 6).put(noWonderLand, 10).build()));

        cityService.save(city18);
        cityService.save(city42);
        cityService.save(moonCity);
        cityService.save(sunnyCity);
        cityService.save(blackDoor);
        cityService.save(castleRock);
        cityService.save(knocksmidt);
        cityService.save(noWonderLand);
        cityService.save(riverran);
        cityService.save(sherwood);
        cityService.save(silentHAven);
        cityService.save(unnamed);
        cityService.save(winterfell);
        cityService.save(yustrown);
    }
}
