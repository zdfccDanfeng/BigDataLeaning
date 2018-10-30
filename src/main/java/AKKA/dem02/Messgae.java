/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package AKKA.dem02;

import java.util.Collections;
import java.util.List;

/**
 *
 * 不可变对象。。
 */
public class Messgae {

    private final int age;

    private final List<String> list;

    public Messgae(int age, List<String> list) {
        this.age = age;
        /**
         * /**
         *          * 把普通list包装为不可变对象
         *          */

        this.list = Collections.unmodifiableList(list);
    }

    public int getAge() {
        return age;
    }

    public List<String> getList() {
        return list;
    }



}
