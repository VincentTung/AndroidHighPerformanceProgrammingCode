package com.vincent.high.performance.memory.flyweight;

public interface Courier<T> {
    void equip(T param);

    int getId();
}
