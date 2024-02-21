package com.superealboom.demo.threads;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * @description: TODO
 * @author: tianci
 * @date: 2022/6/13 12:45
 */
public class ThreadDemo extends Thread {
    public static final byte[] LOCK = new byte[0];
    private static Map<String, String> map = new HashMap<>();
    private static CountDownLatch countDownLatch = new CountDownLatch(2);
    private int threadNo;
    private ThreadDemo(int threadNo) {
        this.threadNo = threadNo;
    }
    public static void main(String[] args) throws Exception {
        for (int i =1;i<=100;i++) {
            map.put("#"+i, "connection:"+i);
        }
        for (int i = 1; i <= 2; i++) {
            new ThreadDemo(i).start();
        }

        countDownLatch.await();
        for (String i : map.keySet()) {
            System.out.println(i + "," + map.get(i));
        }
    }

    @Override
    public void run() {
        if (StringUtils.equals(Thread.currentThread().getName(), "Thread-0")) {
            for (int i=1000;i<1100;i++) {
                System.out.println("add-new-connection:"+i);
                map.put("%"+i,"contain:" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        Iterator<String> iterator;

        synchronized (LOCK) {
            System.out.println("start---" + Thread.currentThread().getName());
            // try {
            //     Thread.sleep(2000);
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }
            iterator = map.keySet().iterator();
            while(iterator.hasNext()){
                String str = iterator.next();
                if(str.contains("#")) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("remove-old-connection:"+str);
                    iterator.remove();
                    countDownLatch.countDown();
                }
            }
            System.out.println("end---" + Thread.currentThread().getName());
        }
    }
}