/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package java8.model;

import java8.ItemType;

public class RecItem {
    private String itemId;
    private ItemType itemType;

    public RecItem() {
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public RecItem(String itemId, ItemType itemType) {
        this.itemId = itemId;
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        return "RecItem{" + "itemId='" + itemId + '\'' + ", itemType=" + itemType + '}';
    }
}
