package com.web.rpg.model.abilities;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.abilities.strategies.AbstractDefaultMagicStrategy;
import com.web.rpg.service.character.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class MagicStrategyFactory {

    private final Map<MagicClasses, AbstractDefaultMagicStrategy> magicStrategies;
    private final CharacterService characterService;

    @Autowired
    public MagicStrategyFactory(List<AbstractDefaultMagicStrategy> abstractDefaultMagicStrategies,
                                CharacterService characterService) {
        magicStrategies = abstractDefaultMagicStrategies.stream().collect(Collectors.toMap(AbstractDefaultMagicStrategy::getMagicClass, Function.identity()));
        this.characterService = characterService;
    }

    public void useMagic(PlayerCharacter character) {
        AbstractDefaultMagicStrategy magicStrategy = magicStrategies.get(character.getMagic().getMagicClass());
        if (magicStrategy != null) {
            magicStrategy.useMagic(character);
            characterService.save(character);
        }
    }
}
