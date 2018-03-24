package com.vincent.high.performance.battery;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;

/**
 * 充电状态变化
 */
public class PowerConnectionBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = PowerConnectionBroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;

        int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean isUSBCharging = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean isACCharging = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

        Log.d(TAG, "onReceive: isCharging:" + isCharging + " isUSBCharging:" + isUSBCharging + " isACCharging:" + isACCharging);

    }
}
