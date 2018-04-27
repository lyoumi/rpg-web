package com.web.rpg.model.abilities.magicStyle;

import com.web.rpg.model.Characters.CharacterClass;
import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.abilities.Magic;
import com.web.rpg.model.abilities.buffs.buffsclasses.DragonForm;
import com.web.rpg.model.abilities.buffs.buffsclasses.ForceOfJedi;
import com.web.rpg.model.abilities.instants.instantmagics.combat.DragonBall;
import com.web.rpg.model.abilities.instants.instantmagics.combat.FireBall;
import com.web.rpg.model.abilities.instants.instantmagics.combat.IceChains;
import com.web.rpg.model.abilities.instants.instantmagics.combat.WindsOfWinter;
import com.web.rpg.model.abilities.instants.instantmagics.healing.SmallHealing;

import java.util.ArrayList;

/**
 * Класс, дающий персонажу соответствующий его классу набор магий.
 */
public class Style {
    public static ArrayList<Magic> getMagicStyle(PlayerCharacter character) {
        ArrayList<Magic> listOfMagic = new ArrayList<>();
        if (CharacterClass.ARCHER.equals(character.getCharacterClass())) {
            listOfMagic.add(FireBall.magicFactory.getMagicFactory(character.getLevel()));
            listOfMagic.add(IceChains.magicFactory.getMagicFactory(character.getLevel()));
            listOfMagic.add(SmallHealing.magicFactory.getMagicFactory(character.getLevel()));
            return listOfMagic;
        } else if (CharacterClass.BERSERK.equals(character.getCharacterClass())) {
            listOfMagic.add(DragonBall.magicFactory.getMagicFactory(character.getLevel()));
            listOfMagic.add(DragonForm.magicFactory.getMagicFactory(character.getLevel()));
            listOfMagic.add(SmallHealing.magicFactory.getMagicFactory(character.getLevel()));
            return listOfMagic;
        } else {
            listOfMagic.add(WindsOfWinter.magicFactory.getMagicFactory(character.getLevel()));
            listOfMagic.add(SmallHealing.magicFactory.getMagicFactory(character.getLevel()));
            listOfMagic.add(ForceOfJedi.magicFactory.getMagicFactory(character.getLevel()));
            return listOfMagic;
        }
    }
}
