package com.vincent.high.performance.memory;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 *
 * 防止Handler持有Activity的引用
 */
public class WeakHandler extends Handler {
    public interface HandleListener {

        void handleMessage(Message msg);
    }

    private WeakReference<HandleListener> mHandleListener = null;

    public WeakHandler(HandleListener listener) {
        mHandleListener = new WeakReference<HandleListener>(listener);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (mHandleListener != null && mHandleListener.get() != null) {
            mHandleListener.get().handleMessage(msg);
        }
    }
}
