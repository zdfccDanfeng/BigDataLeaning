/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package AKKA;

import akka.actor.UntypedActor;

/**
 * 打印招呼
 * @author SUN
 * @version 1.0
 * @Date 16/1/6 21:45
 */
public class GreetPrinter extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Greeeting)
            System.out.println("wocha"+((Greeeting) message).message);
    }
}
