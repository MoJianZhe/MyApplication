package com.dyoon.myapplication.service;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Android 中常见的四种线程池
 * Created by jun on 2017/3/29.
 */

public class ThreadFactor {

    ScheduledExecutorService ses = Executors.newScheduledThreadPool(4);
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
    ExecutorService CachedThreadPool=Executors.newCachedThreadPool();
    ExecutorService sigleThreadPool=Executors.newSingleThreadExecutor();

}

