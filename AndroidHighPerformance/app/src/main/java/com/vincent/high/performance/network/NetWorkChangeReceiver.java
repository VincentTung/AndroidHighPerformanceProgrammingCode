package com.vincent.high.performance.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

/**
 * 网络状态变化监听
 */
public class NetWorkChangeReceiver extends BroadcastReceiver {
    private static final String ACTION_NET_CHANGED = "android.net.conn.CONNECTIVITY_CHANGE";
    private static BroadcastReceiver mReceiver = null;

    /**
     * android M以上需动态注册 监听网络变化
     *
     * @param context
     */
    public static void registerNetWorkReceiver(Context context) {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(ACTION_NET_CHANGED);
            if (null == mReceiver) {
                mReceiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        checkNetState(context);
                    }
                };
                context.registerReceiver(mReceiver, filter);
            }
        }

    }

    private static void checkNetState(Context context) {
        //ACCESS_NETWORK_STATE 需要做权限检查
        final ConnectivityManager connectionManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo wifi = connectionManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        final NetworkInfo mobile = connectionManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi.isAvailable() || mobile.isAvailable()) {


        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        checkNetState(context);
    }

}
