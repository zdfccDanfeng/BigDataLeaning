/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package AKKA.dem02;
import com.alibaba.fastjson.JSONObject;

import akka.actor.UntypedActor;

public class Greeter extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {


        try {
            System.out.println("Greeter 收到消息为："+JSONObject.toJSONString(message));
            getSender().tell("Greeter 工作完成.",getSelf());//给消息发送者发送消息收到的反馈。。
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }





}
