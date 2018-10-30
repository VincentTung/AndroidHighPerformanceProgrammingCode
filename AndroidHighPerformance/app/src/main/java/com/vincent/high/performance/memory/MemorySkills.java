package com.vincent.high.performance.memory;


import android.app.ActivityManager;
import android.content.Context;
import android.support.v4.app.ActivityManagerCompat;
import android.util.Log;

public class MemorySkills {
    private static final String TAG = MemorySkills.class.getSimpleName();

    /**
     * 非线程安全
     *
     * @param args
     * @param <T>
     * @return
     */
    @SafeVarargs
    public static <T> String getString(T... args) {
        StringBuilder sb = new StringBuilder();
        for (T t : args) {
            sb.append(t);
        }
        return sb.toString();

    }

    /**
     * 线程安全
     *
     * @param args
     * @param <T>
     * @return
     */
    @SafeVarargs
    public static <T> String getStringSync(T... args) {
        StringBuffer sb = new StringBuffer();
        for (T t : args) {
            sb.append(t);
        }
        return sb.toString();

    }

    /**
     * @param context
     * @return
     */
    public boolean isLowRam(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        return ActivityManagerCompat.isLowRamDevice(activityManager);
    }


    /**
     * 查看分配内存大小
     *
     * @param context
     */
    public void catMemoryInfo(Context context) {
        ActivityManager am = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        //系统分配的app内存
        int size = am.getMemoryClass();
        //manifest.xml largeHeap =true分配的内存
        int largeSize = am.getLargeMemoryClass();
        Log.d(TAG, "appAllocteMem:" + size + "MB");
        Log.d(TAG, "appAllocteLargeMem:" + largeSize + "MB");

        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(memoryInfo);

        long availmen = memoryInfo.availMem;
        boolean lowMemory = memoryInfo.lowMemory;
        long threshold = memoryInfo.threshold;
        long totalMem = memoryInfo.totalMem;

        Log.d(TAG,"memoryInfo: avalimen:"+availmen+" lowMemory:"+lowMemory+" treshold:"+threshold+" totalMem:"+totalMem);


        ActivityManager.RunningAppProcessInfo  runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(runningAppProcessInfo);

        int lastTrimLevel = runningAppProcessInfo.lastTrimLevel;
        int importance = runningAppProcessInfo.importance;

        Log.d(TAG,"runningAppProcessInfo: lastTrimLevel:"+lastTrimLevel+" importance:"+importance);


    }
}
