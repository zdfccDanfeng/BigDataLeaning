/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package java8.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GlobalThreadPool {

    final static int numOfCpuCores=Runtime.getRuntime().availableProcessors();
    final static double blockingCoefficient=0.9;//阻尼系数

    final static int maxiumPoolSize= (int) (numOfCpuCores/(1-blockingCoefficient));

   static ExecutorService threadPool=new ThreadPoolExecutor(numOfCpuCores,
                                     maxiumPoolSize,
                                     0l,
                                     TimeUnit.MICROSECONDS,
                                      new LinkedBlockingDeque<Runnable>(),
                                      Executors.privilegedThreadFactory(),
                                      new ThreadPoolExecutor.DiscardOldestPolicy());

}
