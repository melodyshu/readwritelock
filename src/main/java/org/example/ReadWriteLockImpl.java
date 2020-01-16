package org.example;

public class ReadWriteLockImpl implements ReadWriteLock {
    private final Object MUTEX=new Object();

    private int writingWriters=0;

    private int waitingWriters=0;

    private int readingReaders=0;

    private boolean perferWriter;

    public ReadWriteLockImpl() {
        this(true);
    }

    public ReadWriteLockImpl(boolean perferWriter) {
        this.perferWriter = perferWriter;
    }

    @Override
    public Lock readLock() {
        return new ReadLock(this);
    }


    @Override
    public Lock writeLock() {
        return new WriterLock(this);
    }

    void incrementWritingWriters(){
        this.writingWriters++;
    }

    void incrementWaitingWriters(){
        this.waitingWriters++;
    }

    void incrementReadingReaders(){
        this.readingReaders++;
    }

    void decrementWritingWriters(){
        this.writingWriters--;
    }

    void decrementWaitingWriters(){
        this.waitingWriters--;
    }

    void decrementReadingReaders(){
        this.readingReaders--;
    }

    @Override
    public int getWritingWriters() {
        return this.writingWriters;
    }

    @Override
    public int getWaitingWriters() {
        return this.waitingWriters;
    }

    @Override
    public int getReadingReaders() {
        return this.readingReaders;
    }

    public Object getMUTEX() {
        return MUTEX;
    }

    public boolean getPerferWriter() {
        return perferWriter;
    }

    void changePrefer(boolean perferWriter){
        this.perferWriter=perferWriter;
    }
}
