package com.vincent.high.performance.battery;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 *
 */
public class BatteryLevelBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = BatteryLevelBroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action.equals(Intent.ACTION_BATTERY_LOW)) {


        } else if (action.equals(Intent.ACTION_BATTERY_OKAY)) {

        }

        float batteryPercentage = BatterySkills.getBatteryPercent(intent);
        Log.d(TAG, "onReceive: " + batteryPercentage);


    }


}
