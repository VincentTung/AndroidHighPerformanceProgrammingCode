package com.vincent.high.performance.battery;


import android.content.Intent;
import android.os.BatteryManager;

public class BatterySkills {

    /**
     * 获取手机电量
     *
     * @param intentBatteryStatus
     * @return
     */
    public static float getBatteryPercent(Intent intentBatteryStatus) {
        int level = intentBatteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intentBatteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        float batteryPercentage = level / (float) scale;
        return batteryPercentage;
    }
}
