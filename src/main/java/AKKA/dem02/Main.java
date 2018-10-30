/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package AKKA.dem02;

import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {

    public static void main(String [] args){

        ActorSystem actorSystem=ActorSystem.create("Hello",ConfigFactory.load("akka.config"));


        ActorRef a=actorSystem.actorOf(Props.create(HelloWord.class),"HelloWord");

        System.out.println(a.path());

    }
}
