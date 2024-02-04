package com.gildedtros;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedTrosTest {

    @Test
    void item_sellInAboveZero() {
        Item[] items = new Item[] { new Item("foo", 2, 2) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(app.getItems()[0].sellIn, 1);
        assertEquals(app.getItems()[0].quality, 1);
    }

    @Test
    void item_sellInEqualsZero() {
        Item[] items = new Item[] { new Item("foo", 0, 2) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();
        assertEquals(app.getItems()[0].sellIn, -1);
        assertEquals(app.getItems()[0].quality, 0);
    }

    @Test
    void wine_increaseInQualityEvenPastSellInDate() {
        Item[] items = new Item[] { new Item("Good Wine", -10, 0) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();

        assertEquals(app.getItems()[0].sellIn, -11);
        assertEquals(app.getItems()[0].quality, 2);
    }

    @Test
    void wine_qualityNeverMoreThan50() {
        Item[] items = new Item[] { new Item("Good Wine", 10, 50) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();

        assertEquals(app.getItems()[0].sellIn, 9);
        assertEquals(app.getItems()[0].quality, 50);
    }

    @Test
    void bDawgKeychain_noChange() {
        Item[] items = new Item[] { new Item("B-DAWG Keychain", 10, 12) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();

        assertEquals(app.getItems()[0].sellIn, 10);
        assertEquals(app.getItems()[0].quality, 12);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Backstage passes for Re:Factor", "Backstage passes for HAXX"})
    void backstagePasses_increaseInQuality_sellInHigherThan10(String name) {
        Item[] items = new Item[] { new Item(name, 11, 20) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();

        assertEquals(app.getItems()[0].sellIn, 10);
        assertEquals(app.getItems()[0].quality, 21);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Backstage passes for Re:Factor", "Backstage passes for HAXX"})
    void backstagePasses_increaseInQualiy_sellInLowerThan10(String name) {
        Item[] items = new Item[] { new Item(name, 10, 20) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();

        assertEquals(app.getItems()[0].sellIn, 9);
        assertEquals(app.getItems()[0].quality, 22);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Backstage passes for Re:Factor", "Backstage passes for HAXX"})
    void backstagePasses_increaseInQuality_sellInLowerThan5(String name) {
        Item[] items = new Item[] { new Item(name, 5, 20) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();

        assertEquals(app.getItems()[0].sellIn, 4);
        assertEquals(app.getItems()[0].quality, 23);
    }


    @ParameterizedTest
    @ValueSource(strings = {"Duplicate Code", "Long Methods", "Ugly Variable Names"})
    void smellyItems_decreaseInQuality_sellInAboveZero(String name) {
        Item[] items = new Item[] { new Item(name, 10, 20) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();

        assertEquals(app.getItems()[0].sellIn, 9);
        assertEquals(app.getItems()[0].quality, 18);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Duplicate Code", "Long Methods", "Ugly Variable Names"})
    void smellyItems_decreaseInQuality_sellInZeroOrLower(String name) {
        Item[] items = new Item[] { new Item(name, 0, 20) };
        GildedTros app = new GildedTros(items);
        app.updateQuality();

        assertEquals(app.getItems()[0].sellIn, -1);
        assertEquals(app.getItems()[0].quality, 16);
    }
}
