package com.vincent.high.performance.memory;

import java.lang.ref.WeakReference;

public class Singleton {
    private static Singleton singleton;
    private WeakReference<Callback> callback;

    public static Singleton getInstance() {
        if (singleton == null)
            singleton = new Singleton();
        return singleton;
    }

    public Callback getCallback() {
        return callback.get();
    }

    public void setCallback(Callback callback) {
        this.callback = new WeakReference<Callback>(callback);
    }

    public interface Callback {
        void callback();
    }
}