package com.web.rpg.events.impl;

import com.web.rpg.events.EventProcessorService;
import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Characters.Story;
import com.web.rpg.model.Quests.Quest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class EventProcessorServiceImpl implements EventProcessorService {

    private static final Random RANDOM = new Random();

    @Override
    public void generateQuest(PlayerCharacter character) {
        Story story = new Story();
        story.setPrefix(generatePrefix());
        story.setStep(1);
        generateCurrentAction(story);
        character.setStory(story);
        Quest quest = new Quest();
        quest.setTitle(getQuestTitle(story.getPrefix()));
        quest.setExp(RANDOM.nextLong());
        quest.setGold(RANDOM.nextInt(1000));
        character.setQuest(quest);
    }

    @Override
    public void changeEvent(PlayerCharacter character) {
        character.getStory().setStep(character.getStory().getStep() + 1);
        generateCurrentAction(character.getStory());
    }

    private String getQuestTitle(String code) {
        Properties prop = new Properties();
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream("properties/quests.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(code);
    }

    private String generatePrefix() {
        Properties prop = new Properties();
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream("properties/event-codes.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> itemNames = prop.entrySet().stream().map(entry -> (String) entry.getValue()).collect(Collectors.toList());
        return itemNames.get(RANDOM.nextInt(itemNames.size()));
    }

    private void generateCurrentAction(Story story) {
        Properties prop = new Properties();
        try {
            prop.load(getClass().getClassLoader().getResourceAsStream("properties/events.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String property = prop.getProperty(story.getPrefix() + "_" + story.getStep());
        if (StringUtils.isBlank(property)) {
            property = prop.getProperty(story.getPrefix() + "_END");
            story.setEnd(true);
        }
        story.setCurrentAction(property);
    }
}
