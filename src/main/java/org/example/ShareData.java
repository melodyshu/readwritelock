package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShareData {
    private final List<Character> container=new ArrayList<>();
    private final ReadWriteLock readWriteLock=ReadWriteLock.readWriteLock();
    private final Lock readLock=readWriteLock.readLock();
    private final Lock writeLock=readWriteLock.writeLock();
    private final int length;

    public ShareData(int length) {
        this.length = length;
        for (int i = 0; i < length; i++) {
            container.add(i,'c');
        }
    }

    public char[] read() throws InterruptedException{
        char[] newBuffer=new char[length];
        try{
            readLock.lock();
            for (int i = 0; i < length; i++) {
                newBuffer[i]=container.get(i);
            }
            slowly();
        }finally {
            writeLock.unlock();
        }
        return newBuffer;
    }

    public void write(char c) throws InterruptedException {
        try{
            writeLock.lock();
            for (int i = 0; i < length; i++) {
                container.add(i,c);
            }
            slowly();
        }finally {
            writeLock.unlock();
        }
    }

    private void slowly() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
