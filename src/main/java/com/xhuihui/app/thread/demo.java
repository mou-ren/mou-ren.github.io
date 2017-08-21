package com.xhuihui.app.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by lihuiguang on 2017/8/8.
 */
public class demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> a = new FutureTask<String>(new Callable<String>() {
            public String call() throws Exception {
                for (int i =0; i<10; i++){
                    System.out.print(i);
                    Thread.sleep(1000);
                }
                return "sfdsfds";
            }
        });
        System.out.print(a.get());
    }
}
