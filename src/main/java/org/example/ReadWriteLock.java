package org.example;

public interface ReadWriteLock {
    Lock readLock();

    Lock writeLock();

    //获取当前有多少个线程正在进行写操作,最多为1
    int getWritingWriters();

    //获取当前有多少个线程正在等待获取写锁
    int getWaitingWriters();

    //获取当前有多少个线程正在进行读操作
    int getReadingReaders();

    static ReadWriteLock readWriteLock(){
        return new ReadWriteLockImpl();
    }

    static ReadWriteLock readWriteLock(boolean preferWriter){
        return new ReadWriteLockImpl(preferWriter);
    }
}
