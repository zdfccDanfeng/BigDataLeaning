/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package AKKA.dem02;

import java.util.Arrays;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class HelloWord extends UntypedActor {

    ActorRef greeter;
    @Override
    public void onReceive(Object message) {

        try {
            System.out.println("Hello word 收到的数据为："+JSONObject.toJSONString(message));
            getContext().stop(getSelf());//收到消息后，停止发送消息，。，，
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void preStart() throws Exception {

        greeter=getContext().actorOf(Props.create(Greeter.class),"greeter");

        System.out.println("Greeter actor path: "+greeter.path());


        // tell it to preform the greeting

        greeter.tell(new Messgae(2, Arrays.asList("2","dsf")),getSelf());
    }
}
