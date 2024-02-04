package com.gildedtros;

class GildedTros {

  private final Item[] items;

  private final int MAX_QUALITY = 50;
  private final int MIN_QUALITY = 0;

  public GildedTros(Item[] items) {
    this.items = items;
  }

  public void updateQuality() {
    for (Item item : items) {
      item.sellIn = item.sellIn - 1;
      switch (item.name) {
        case "Good Wine" -> updateGoodWine(item);
        case "B-DAWG Keychain" -> item.sellIn++;
        case "Backstage passes for Re:Factor", "Backstage passes for HAXX" ->
            updateBackstagePasses(item);
        case "Duplicate Code", "Long Methods", "Ugly Variable Names" -> updateSmellyItems(item);
        default -> updateDefault(item);
      }
    }
  }

  private void updateGoodWine(Item wine) {
    wine.quality = Math.min(wine.quality + 2, MAX_QUALITY);
  }

  private void updateBackstagePasses(Item backstagePass) {
    if (backstagePass.sellIn < 0) {
      backstagePass.quality = MIN_QUALITY;
    } else if (backstagePass.sellIn < 5) {
      backstagePass.quality = Math.min(backstagePass.quality + 3, MAX_QUALITY);
    } else if (backstagePass.sellIn < 10) {
      backstagePass.quality = Math.min(backstagePass.quality + 2, MAX_QUALITY);
    } else {
      backstagePass.quality = Math.min(backstagePass.quality + 1, MAX_QUALITY);
    }
  }

  private void updateSmellyItems(Item item) {
    if (item.sellIn < 0) {
      item.quality = Math.max(item.quality - 4, MIN_QUALITY);
    } else {
      item.quality = Math.max(item.quality - 2, MIN_QUALITY);
    }
  }

  private void updateDefault(Item item) {
    if (item.sellIn < 0) {
      item.quality = Math.max(item.quality - 2, MIN_QUALITY);
    } else {
      item.quality = Math.max(item.quality - 1, MIN_QUALITY);
    }
  }

  public Item[] getItems() {
    return items;
  }
}
