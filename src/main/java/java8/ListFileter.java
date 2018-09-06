/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package java8;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.twitter.util.Awaitable;
import com.twitter.util.Duration;
import com.twitter.util.Future;
import com.twitter.util.FuturePools;
import com.twitter.util.TimeoutException;
import com.twitter.util.Try;

import static com.twitter.util.Function.func0;
import java8.model.RecItem;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Tuple2;
import scala.runtime.BoxedUnit;

public class ListFileter {
   //static CountDownLatch countDownLatch=new CountDownLatch(2);
    public static void main(String [] args) {

        RecItem recItem = new RecItem("7", ItemType.Huodong);
        RecItem recItem1 = new RecItem("2", ItemType.Huodong);
        RecItem recItem2 = new RecItem("3", ItemType.Huodong);
        RecItem recItem3 = new RecItem("4", ItemType.Shangpin);
        RecItem recItem4 = new RecItem("5", ItemType.Huodong);
        RecItem recItem5 = new RecItem("6", ItemType.Shangpin);

        List <RecItem> recItemList = Arrays.asList(recItem, recItem1, recItem2, recItem3, recItem4, recItem5);

        List <RecItem> top = recItemList.stream().filter(rec -> rec.getItemType().equals(ItemType.Huodong)).limit(5).collect(Collectors.toList());

        Instant nowTime = Instant.now();

        // countDownLatch.await();
        Future <List <RecItem>> listFuture1 = FuturePools.unboundedPool().apply(func0(() -> someIO()));
        Future <List <RecItem>> listFuture2 = FuturePools.unboundedPool().apply(func0(() -> fec()));
        ;



        System.out.println("任务耗时：" + (Instant.now().getEpochSecond() - nowTime.getEpochSecond()));

    }

    private static List<RecItem> fec() {
        List<RecItem> recItemList=new ArrayList <>();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        recItemList.add(new RecItem("1",ItemType.Shangpin));
        recItemList.add(new RecItem("2",ItemType.Shangpin));
        //countDownLatch.countDown();
        return recItemList;

    }

    private static List<RecItem> someIO() {

        //加工生产RecItem
        ScheduledExecutorService scheduledExecutorService=Executors.newScheduledThreadPool(2);
        List<RecItem> recItemList=new ArrayList <>();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        recItemList.add(new RecItem("21",ItemType.Huodong));
        recItemList.add(new RecItem("22",ItemType.Huodong));
        //countDownLatch.countDown();
        return recItemList;
    }
}
