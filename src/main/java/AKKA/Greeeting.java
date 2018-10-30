/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package AKKA;

import java.io.Serializable;

/**
 *
 * 招呼体,里面有打的什么招呼
 */
public class Greeeting implements Serializable {

    public final String message;

    public Greeeting(String message) {
        this.message = message;
    }
}
