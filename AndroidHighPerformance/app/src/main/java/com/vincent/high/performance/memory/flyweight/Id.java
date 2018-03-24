package com.vincent.high.performance.memory.flyweight;

public abstract class Id {
    private int id;

    public Id(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
