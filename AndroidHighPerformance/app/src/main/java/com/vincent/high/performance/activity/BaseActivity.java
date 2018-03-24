package com.vincent.high.performance.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.vincent.high.performance.memory.WeakHandler;


public class BaseActivity extends AppCompatActivity implements WeakHandler.HandleListener {

    protected WeakHandler mWeakHandler = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeakHandler = new WeakHandler(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWeakHandler.removeCallbacksAndMessages(null);
        mWeakHandler = null;
    }

    @Override
    public void handleMessage(Message msg) {

    }
}
