package com.vincent.high.performance.network;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class NetWorkSkill {

    /**
     * 检查网络是否连接
     */
    public static boolean isNetConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetWorkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetWorkInfo != null && activeNetWorkInfo.isConnectedOrConnecting();

    }

    /**
     * 是否是wifi
     * @param context
     * @return
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetWorkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetWorkInfo != null && activeNetWorkInfo.isConnectedOrConnecting() && activeNetWorkInfo.getType() == connectivityManager.TYPE_WIFI;

    }

    /**
     * 获取wifi速度
     * @param context
     * @return
     */
    public static int getWifiSpeed(Context context) {

        if (isWifi(context)) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            //需要检查WIFI_STATE权限
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            //速度：Mb
            int speedMbps = wifiInfo.getLinkSpeed();
            return speedMbps;

        } else {
            return 0;
        }


    }
}
