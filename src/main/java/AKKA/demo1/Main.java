/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package AKKA.demo1;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;


public class Main {

    public static void main(String [] args) {

        ActorSystem system = ActorSystem.create("Hello");
        ActorRef a = system.actorOf(Props.create(HelloWord.class), "helloWorld");
        System.out.println(a.path());

    }
}
