package com.vincent.high.performance;


import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Build;

public class PerformanceApplication  extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        switch (level) {
            case TRIM_MEMORY_COMPLETE:
                //程序不可见-内存低-位于LRU底部
                break;
            case TRIM_MEMORY_MODERATE:
                //程序不可见-内存低-位于LRU中部
                break;
            case TRIM_MEMORY_BACKGROUND:
                //程序不可见-内存低-位于LRU顶部
                break;
            case TRIM_MEMORY_UI_HIDDEN:
                //程序不可见-位于LRU顶部
                break;
            case TRIM_MEMORY_RUNNING_CRITICAL:
                //程序可见-内存紧张-位于LRU顶部
                break;
            case TRIM_MEMORY_RUNNING_LOW:
                //程序可见-内存低-位于LRU顶部
                break;
            case TRIM_MEMORY_RUNNING_MODERATE:
                //程序可见-内存较少-位于LRU顶部
                break;
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }

    private void registNetWorkCallback() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NetworkRequest.Builder builder = new NetworkRequest.Builder();
            NetworkRequest request = builder.build();
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            connectivityManager.registerNetworkCallback(request, new ConnectivityManager.NetworkCallback() {

                @Override
                public void onAvailable(Network network) {
                    super.onAvailable(network);
                }

                @Override
                public void onLost(Network network) {
                    super.onLost(network);
                }
            });
        }
    }
}
