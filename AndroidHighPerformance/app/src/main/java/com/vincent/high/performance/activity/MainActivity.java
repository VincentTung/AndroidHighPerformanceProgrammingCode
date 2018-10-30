package com.vincent.high.performance.activity;

import android.content.ComponentCallbacks;
import android.content.ComponentCallbacks2;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.vincent.high.performance.R;
import com.vincent.high.performance.battery.BatterySkills;
import com.vincent.high.performance.entity.News;
import com.vincent.high.performance.other.OnNewsSelected;

public class MainActivity extends BaseActivity implements OnNewsSelected, View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWeakHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setTitle("aaaaaaa");
            }
        }, 100000);

        findViewById(R.id.btn_battery).setOnClickListener(this);


        registerComponentCallbacks(new ComponentCallbacks2() {
            @Override
            public void onTrimMemory(int level) {

            }

            @Override
            public void onConfigurationChanged(Configuration newConfig) {

            }

            @Override
            public void onLowMemory() {

            }
        });
    }

    @Override
    public void onNewsClick(News news) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * doze模式下 设置电量优化白名单
             */
            case R.id.btn_battery:
                boolean isInWhiteList = BatterySkills.isIgnoringBatteryOptimizations(this);
                if (!isInWhiteList) {
                    BatterySkills.requestIgnoreBatteryOptimizations(this);
                } else {
                    toast("已在电量优化白名单中");
                }
                break;
            default:
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == BatterySkills.REQUEST_IGNORE_BATTERY_CODE) {
            if (requestCode == RESULT_OK) {
                toast("设置电量优化白名单成功");
            } else {
                toast("设置电量优化白名单失败");
            }
        }
    }


    private void toast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
