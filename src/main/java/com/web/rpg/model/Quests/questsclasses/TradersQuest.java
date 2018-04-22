package com.web.rpg.model.Quests.questsclasses;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.model.Items.itemsclasses.Item;
import com.web.rpg.model.Quests.Quest;
import com.web.rpg.model.Quests.QuestFactory;

public class TradersQuest implements Quest {

    private final int experience;
    private final int task;

    private int last;

    private TradersQuest(PlayerCharacter character){
        task = character.getLevel() * 10;
        last = task;
        if (character.getLevel() < 8) experience = character.getLevel()*100;
        else experience = character.getLevel()*10000;
    }

    @Override
    public int getReward() {
        return experience;
    }

    @Override
    public int getTask() {
        return task;
    }

    @Override
    public int getLast() {
        last--;
        return last;
    }

    @Override
    public Item getItemReward() {
        return null;
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": kill " + getTask() + " enemy; reward: " + getReward() + " exp";
    }

    public static QuestFactory questFactory = TradersQuest::new;
}
