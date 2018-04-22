package com.web.rpg.service.characterServices;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.EquipmentItems;
import com.web.rpg.model.Items.itemsclasses.Item;
import com.web.rpg.repository.CharacterRepository;
import com.web.rpg.service.characterServices.interfaces.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.stream.Collectors;

public class CharacterServiceImpl implements CharacterService {

    @Override
    public void processCharacters() {

    }

    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public Integer getCountOfManaBootles(String id) {
        if (id == null) {
            return null;
        }
        PlayerCharacter character = characterRepository.findOne(id);
        return character.getCountOfBigManaPointBottles() +
                character.getCountOfMiddleManaPointBottles() +
                character.getCountOfSmallManaPointBottles();
    }

    @Override
    public Integer getCountOfHitPointBootles(String id) {
        if (id == null) {
            return null;
        }
        PlayerCharacter character = characterRepository.findOne(id);
        return character.getCountOfBigHitPointBottle() +
                character.getCountOfMiddleHitPointBottle() +
                character.getCountOfSmallHitPointBottle();
    }

    private Map<EquipmentItems, Item> getCharacterEquipment(String id) {
        if (id == null) {
            return null;
        }
        PlayerCharacter character = characterRepository.findOne(id);
        return character.getArmor()
                .stream()
                .collect(Collectors.toMap(Item::getEquipment, armor -> armor, (a, b) -> b));
    }
}
