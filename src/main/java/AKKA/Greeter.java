/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package AKKA;

import akka.actor.UntypedActor;

/**
 *
 * 打招呼的Actor
 */
public class Greeter extends UntypedActor {

    String greeting = "";

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof WhoToGreet)
            greeting = "hello, " + ((WhoToGreet) message).who;
        else if (message instanceof Greet)
            // 发送招呼消息给发送消息给这个Actor的Actor
            // in fact xx.tell ==== tell.xx的效果。。。
            getSender().tell(new Greeeting(greeting), getSelf());
        else unhandled(message);
    }
}
