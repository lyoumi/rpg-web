package com.web.rpg.model.Quests;

import com.web.rpg.model.Items.itemsclasses.Item;

public interface Quest {
    
    int getReward();
    
    int getTask();

    int getLast();

    Item getItemReward();

    void finalize() throws Throwable;
}
