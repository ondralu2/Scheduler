package com.project.scheduler.web;

public class Counter {
    private int count;

    public Counter() {
        count = 0;
    }

    public int getAndIncCount() {
        return ++count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
