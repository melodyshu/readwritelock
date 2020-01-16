package org.example;

import java.util.concurrent.TimeUnit;

public class ReadLock implements Lock {
    private final ReadWriteLockImpl readWriteLock;

    public ReadLock(ReadWriteLockImpl readWriteLock) {
        this.readWriteLock=readWriteLock;
    }


    @Override
    public void lock() throws InterruptedException {
        synchronized (readWriteLock.getMUTEX()){
            while (readWriteLock.getWritingWriters()>0 || (readWriteLock.getPerferWriter() && readWriteLock.getWaitingWriters()>0)){
                readWriteLock.getMUTEX().wait();
            }
            readWriteLock.incrementReadingReaders();
            TimeUnit.SECONDS.sleep(3);
            System.out.println("读锁....");
        }

    }

    @Override
    public void unlock() {
        synchronized (readWriteLock.getMUTEX()){
            readWriteLock.decrementReadingReaders();
            readWriteLock.changePrefer(true);
            readWriteLock.getMUTEX().notifyAll();
        }
    }
}
