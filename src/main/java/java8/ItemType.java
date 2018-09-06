/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package java8;

public enum ItemType {
    Huodong("huodong"),
    Shangpin("shangpin"),
    None("None");
    private String name;

    /**
     * 定义方法，返回描述
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 私有构造器，防止被外部使用
     * @param huodong
     */
    private ItemType(String huodong) {

    }

    public ItemType getItemTypeByName(String name){
        for (ItemType itemType:ItemType.values()){
            if(name.equals(itemType.getName())){
                return itemType;
            }
        }
        return ItemType.None;
    }

}
