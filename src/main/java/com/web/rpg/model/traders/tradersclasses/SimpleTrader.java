package com.web.rpg.model.traders.tradersclasses;

import com.web.rpg.model.Characters.PlayerCharacter;
import com.web.rpg.ext.RandomUniqueValue;
import com.web.rpg.model.Characters.characterclasses.Archer;
import com.web.rpg.model.Characters.characterclasses.Berserk;
import com.web.rpg.model.Items.itemsclasses.HealingItems;
import com.web.rpg.model.Items.itemsclasses.Item;
import com.web.rpg.model.Items.itemsclasses.armorsclasses.armors.LegendaryArmor;
import com.web.rpg.model.Items.itemsclasses.armorsclasses.boots.LegendaryBoots;
import com.web.rpg.model.Items.itemsclasses.armorsclasses.helmets.LegendaryHelmet;
import com.web.rpg.model.Items.itemsclasses.healclasses.healHitPoint.items.BigHPBottle;
import com.web.rpg.model.Items.itemsclasses.healclasses.healManaPoint.items.BigManaPointBottle;
import com.web.rpg.model.Items.itemsclasses.weaponsclasses.weapons.archer.LegendaryBow;
import com.web.rpg.model.Items.itemsclasses.weaponsclasses.weapons.berserk.LegendaryBoxingGloves;
import com.web.rpg.model.Items.itemsclasses.weaponsclasses.weapons.wizard.LegendaryStaff;
import com.web.rpg.model.Quests.Quest;
import com.web.rpg.model.Quests.questsclasses.LegendaryQuest;
import com.web.rpg.model.Quests.questsclasses.TradersQuest;
import com.web.rpg.model.traders.Trader;
import com.web.rpg.model.traders.TradersFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс-реализация торговца.
 */
public class SimpleTrader implements Trader {

    /**
     * Создаем экземпляр класса для генерации случайного значения типа int.
     */
    private RandomUniqueValue randomUniqueValue = new RandomUniqueValue();

    /**
     * Мар, содержащая id предмета и предметы экипировки.
     */
    private Map<Integer, Item> priceListEquipmentObjects = new LinkedHashMap<>();

    /**
     * Мар, содержащая id предмета и предметы для восстановления здоровья/маны.
     */
    private Map<Integer, HealingItems> priceListHealingObjects = new LinkedHashMap<>();

    /**
     * Объект типа {@link PlayerCharacter} хранящий в себе имплементацию конкретного персонажа.
     */
    private PlayerCharacter character;

    /**
     * Конструктор, инициализирующий map'ы предметов и объект.
     * Также вызывается метод заполняющий map'ы объектами.
     *
     * @param character
     *              character implementation of {@link PlayerCharacter}
     */
    private SimpleTrader(PlayerCharacter character){
        this.character = character;
        generatePriceList();
    }

    /**
     * Метод, заполняющий map'ы предметами и их id.
     */
    private void generatePriceList(){
        if (character instanceof Archer) priceListEquipmentObjects.put(randomUniqueValue.nextUniqueInt(), LegendaryBow.itemsFactory.createNewItem(character));
        else if (character instanceof Berserk) priceListEquipmentObjects.put(randomUniqueValue.nextUniqueInt(), LegendaryBoxingGloves.itemsFactory.createNewItem(character));
        else priceListEquipmentObjects.put(randomUniqueValue.nextUniqueInt(), LegendaryStaff.itemsFactory.createNewItem(character));
        priceListEquipmentObjects.put(randomUniqueValue.nextUniqueInt(), LegendaryHelmet.itemsFactory.createNewItem(character));
        priceListEquipmentObjects.put(randomUniqueValue.nextUniqueInt(), LegendaryBoots.itemsFactory.createNewItem(character));
        priceListEquipmentObjects.put(randomUniqueValue.nextUniqueInt(), LegendaryArmor.itemsFactory.createNewItem(character));
        priceListHealingObjects.put(randomUniqueValue.nextUniqueInt(), BigManaPointBottle.healingHitPointItemsFactory.getNewHealingManaPointItem());
        priceListHealingObjects.put(randomUniqueValue.nextUniqueInt(), BigHPBottle.healingHitPointItemsFactory.getNewHealingHitPointItem());
    }

    @Override
    public Item getEquipmentItem(int id) {
        Item item = priceListEquipmentObjects.get(id);
        priceListEquipmentObjects.remove(id);
        return item;
    }

    @Override
    public List<HealingItems> getHealItems(int count, int id) {
        List<HealingItems> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(priceListHealingObjects.get(id));
        }
        return list;
    }

    @Override
    public Map<Integer, Item> getPriceListEquipmentObjects() {
        return priceListEquipmentObjects;
    }

    @Override
    public Map<Integer, HealingItems> getPriceListHealingObjects() {
        return priceListHealingObjects;
    }

    @Override
    public Quest getQuest(int index) {
        if (index==1) return TradersQuest.questFactory.createNewQuest(character);
        else return LegendaryQuest.questFactory.createNewQuest(character);
    }

    public static TradersFactory tradersFactory = SimpleTrader::new;
}
