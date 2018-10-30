/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package AKKA;

import java.io.Serializable;

public class WhoToGreet implements Serializable {

    public final String who;

    public WhoToGreet(String who) {
        this.who = who;
    }
}
