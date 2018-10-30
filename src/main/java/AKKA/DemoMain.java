/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package AKKA;

import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;

import scala.concurrent.duration.Duration;

public class DemoMain {
    public static void main(String[] args) throws Exception {
        final ActorSystem system = ActorSystem.create("helloakka");
        // 创建一个到greeter Actor的管道
        final ActorRef greeter = system.actorOf(Props.create(Greeter.class), "greeter");
        // 创建邮箱
        final Inbox inbox = Inbox.create(system);
        // 先发第一个消息,消息类型为WhoToGreet
        /**
         * 表示给greeter这个Actor发送一个消息,消息的内容是WhoToGreet,发送者是空.这就意味着在greeter这个Actor内部,
         * 调用sender是不能获取到发送者的.通过这个动作,就把消息限定为了单向的.
         */
        greeter.tell(new WhoToGreet("akka"), ActorRef.noSender());
        // 真正的发送消息,消息体为Greet
        //**
        /**
         * 再然后,通过inbox.send(greeter, new Greet());
         * ,使用邮箱显示的发送一个Greet消息给greeter.这是给Actor发送消息的另外一种方法,
         * 这种方法通常会有更高的自主性,
         * 能完成更多更复杂的操作.但是调用起来比直接使用ActorRef来的复杂.
         */
        inbox.send(greeter, new Greet());
        // 等待5秒尝试接收Greeter返回的消息
        Greeeting greeting1 = (Greeeting) inbox.receive(Duration.create(5, TimeUnit.SECONDS));
        System.out.println("Greeting: " + greeting1.message);
        // 发送第三个消息,修改名字
        greeter.tell(new WhoToGreet("typesafe"), ActorRef.noSender());
        // 发送第四个消息
        inbox.send(greeter, new Greet());

        // 等待5秒尝试接收Greeter返回的消息
        Greeeting greeting2 = (Greeeting) inbox.receive(Duration.create(5, TimeUnit.SECONDS));
        System.out.println("Greeting: " + greeting2.message);
        // 新创建一个Actor的管道
        ActorRef greetPrinter = system.actorOf(Props.create(GreetPrinter.class));

        //使用schedule 每一秒发送一个Greet消息给 greeterActor,然后把greeterActor的消息返回给greetPrinterActor
        system.scheduler().schedule(Duration.Zero(), Duration.create(1, TimeUnit.SECONDS), greeter, new Greet(), system.dispatcher(), greetPrinter);
        //system.shutdown();
    }
}
