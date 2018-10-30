/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package AKKA.demo1;

import akka.actor.UntypedActor;

public class Greeter extends UntypedActor {

    public static enum Msg{
        GREET,
        DONE;
    }

    @Override
    public void onReceive(Object message) throws InterruptedException {
        if(message==Msg.GREET){
            System.out.println("Hello word");
            Thread.sleep(1000);

            getSender().tell(Msg.DONE,getSelf());

        }else {
            unhandled(message);
        }

    }
}
