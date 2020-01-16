package org.example;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class Syndemo {
    private final Object MUTEX=new Object();
    private volatile static boolean flag=false;
    private volatile static int count=1;
    public void sayHello() throws InterruptedException {
        synchronized (MUTEX){
            while (flag){
                System.out.println(flag);
                System.out.println("wait...");
                this.MUTEX.wait();
            }
            TimeUnit.SECONDS.sleep(5);
            System.out.println("say Hello...");
        }
    }

    public void setFlag(boolean flag){
        this.flag=flag;
    }
}
