package com.web.rpg.controller;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.service.character.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Controller
@RequestMapping(value = "game")
public class BasicController {


    @Autowired
    private CharacterService characterService;

    @PostMapping(path = "create")
    public void create() {
        IntStream.range(0, 100)
                .forEach(value -> generator());
    }

    @GetMapping(path = "show")
    @ResponseBody
    public List<PlayerCharacter> show() {
        return characterService.findAll();
    }

    private void generator() {
        PlayerCharacter character = new PlayerCharacter();
        character.setId(UUID.randomUUID());
        character.setManaPoints(30000D);
        character.setAgility(200);
        character.setPower(200);
        character.setIntelligence(200);
        character.setName(UUID.randomUUID().toString());
        characterService.createOrUpdateCharacter(character);
    }
}
