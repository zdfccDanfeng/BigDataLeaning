/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package AKKA.demo1;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import scala.Option;

public class HelloWord extends UntypedActor
{
    @Override
    public void onReceive(Object message) {

        if (message==Greeter.Msg.DONE){
            System.out.println("i have recevived the msg from"+getSender().path().name());
            // tell it to perform the greeting

           // getContext().stop(getSelf());//收到消息后就停止继续发送消息
            getSender().tell(Greeter.Msg.GREET,getSelf());

        }else {
            unhandled(message);
        }
    }

    @Override
    public void preStart() throws Exception {
        //System.out.println("hhhh");
        final ActorRef greeter=getContext().actorOf(Props.create(Greeter.class),"greeeter");
        // tell it to perform the greeting
        greeter.tell(Greeter.Msg.GREET,getSelf());
    }
}
