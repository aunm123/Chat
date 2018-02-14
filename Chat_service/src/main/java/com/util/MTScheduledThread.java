package com.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MTScheduledThread {

    private ScheduledExecutorService scheduExc;
    private Long start;

    public MTScheduledThread() {
        this.scheduExc = Executors.newScheduledThreadPool(2);
        this.start = System.currentTimeMillis();
    }

    public void newAThread(Runnable run){
        scheduExc.schedule(run,1000, TimeUnit.MILLISECONDS);
    }
    public void newARateThread(Runnable run,long RateTime){
        scheduExc.scheduleWithFixedDelay(run,1000,RateTime, TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args){
        MTScheduledThread test = new MTScheduledThread();
        test.newARateThread(new Runnable() {
            public void run() {
                System.out.println("fuck time:"+System.currentTimeMillis());
            }
        },1000);
    }
}
