package com.vincent.high.performance.activity;

import android.os.Bundle;

import com.vincent.high.performance.R;
import com.vincent.high.performance.entity.News;
import com.vincent.high.performance.other.OnNewsSelected;

public class MainActivity extends BaseActivity implements OnNewsSelected {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWeakHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setTitle("aaaaaaa");
            }
        },100000);
    }

    @Override
    public void onNewsClick(News news) {

    }
}
