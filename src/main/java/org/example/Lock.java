package org.example;

public interface Lock {
    void lock() throws InterruptedException;
    void unlock();
}
