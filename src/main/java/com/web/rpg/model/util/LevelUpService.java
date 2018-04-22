package com.web.rpg.model.util;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Characters.characterclasses.CharacterClass;
import com.web.rpg.model.util.stats.Stats;
import com.web.rpg.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LevelUpService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private ArcherServiceUtil archerServiceUtil;
    @Autowired
    private BerserkServiceUtil berserkServiceUtil;
    @Autowired
    private WizardServiceUtil wizardServiceUtil;

    public void levelUp(String stat, String playerId) {
        PlayerCharacter character = characterRepository.getCharacterByPlayerId(playerId);

        switch (stat) {
            case Stats.AGILITY: {
                character.setAgility(character.getAgility() + 1);
                break;
            }
            case Stats.INTELLIGENCE: {
                character.setIntelligence(character.getIntelligence() + 1);
                break;
            }
            case Stats.POWER: {
                character.setPower(character.getPower() + 1);
                break;
            }
        }
        updateStatsByCharacterClass(character);
    }

    private void updateStatsByCharacterClass(PlayerCharacter character) {


        switch (character.getCharacterClass()) {
            case CharacterClass.ARCHER: {
                updateStats(character, archerServiceUtil.getMultiplierPower(), archerServiceUtil.getMultiplierIntelligence(), archerServiceUtil.getMultiplierAgility()/2);
                break;
            }
            case CharacterClass.BERSERK: {
                updateStats(character, berserkServiceUtil.getMultiplierPower(), berserkServiceUtil.getMultiplierIntelligence(), berserkServiceUtil.getMultiplierAgility());
                break;
            }
            case CharacterClass.WIZZARD: {
                updateStats(character, wizardServiceUtil.getMultiplierPower(), wizardServiceUtil.getMultiplierIntelligence(), wizardServiceUtil.getMultiplierPower());
                break;
            }

        }
        characterRepository.save(character);
    }

    private PlayerCharacter updateStats(PlayerCharacter character, double characterPowerMultiplier, double characterIntelligenceMultiplier, double characterAgilityMultiplier) {
        character.setHitPoints(characterPowerMultiplier * character.getPower());
        character.setManaPoints(characterIntelligenceMultiplier * character.getIntelligence());
        character.setBaseDamage(characterAgilityMultiplier * character.getAgility());
        return character;
    }
}
