package org.example;

import java.util.concurrent.TimeUnit;

public class SynMain {
    public static void main(String[] args) throws InterruptedException {
        Syndemo syndemo=new Syndemo();
        syndemo.setFlag(true);
            new Thread(()->{
                try {
                    syndemo.sayHello();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        TimeUnit.SECONDS.sleep(1);
        syndemo.setFlag(false);
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try {
                    syndemo.sayHello();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }


        //TimeUnit.SECONDS.sleep(10);

        //syndemo.setFlag(true);


    }
}
