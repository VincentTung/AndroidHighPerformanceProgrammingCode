package com.vincent.high.performance.memory;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.util.LongSparseArray;
import android.util.ArrayMap;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;

import com.vincent.high.performance.activity.BaseActivity;
import com.vincent.high.performance.memory.flyweight.Delivery;
import com.vincent.high.performance.memory.flyweight.Destination;
import com.vincent.high.performance.memory.flyweight.Pack;
import com.vincent.high.performance.memory.objectpool.ObjectPool;
import com.vincent.high.performance.memory.objectpool.Task;
import com.vincent.high.performance.memory.objectpool.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;


public class MemoryActivity extends BaseActivity {

    /**
     * 将枚举转换为整形常量
     */
    public static final int RECTANGLE = 0;
    public static final int TRIANGLE = 1;
    public static final int SQUARE = 2;
    public static final int CIRCLE = 3;


    @IntDef({RECTANGLE, TRIANGLE, SQUARE, CIRCLE})
    public @interface Shape {

    }

    private int mShape = CIRCLE;

    @Shape
    public int getShape() {
        return mShape;
    }

    public void setShape(@Shape int shape) {
        this.mShape = shape;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sparseLearn();
        objectPoolLearn();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void sparseLearn() {

        /**
         *
         SparseArray 避免了基本类型的拆装箱
         内部采用两个数组来存储 key[]   valuep[]
         put的时候采用二分查找方法 找key的下标，如果找到就进行替换，否则进行array插入操作
         put(int,E)   append(int,E) 区别
         put()先查找key，找到替换，找不到就根据计算出来的下标进行插入操作
         append是直接在末尾插入元素

         */

        SparseArray<String> stringSparseArray = new SparseArray<>();
        stringSparseArray.put(1, "SparseArray");

        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        sparseBooleanArray.put(1, true);

        SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.append(1, 1);

        SparseLongArray sparseLongArray = new SparseLongArray();
        sparseLongArray.put(1, 100000L);

        LongSparseArray<String> longSparseArray = new LongSparseArray<>();
        longSparseArray.put(100000L, "LongSparseArray");

        /**
         * ArrayMap  存储<Object,Object>类型
         采用两个数组来存储  key的hash[]值  和value[]
         put的时候采用二分查找法 查找下标
         */

        ArrayMap<Integer, String> arrayMap = new ArrayMap<>();
        for (int index = 0; index < 10; index++) {
            arrayMap.put(index, String.valueOf(index));
        }

    }


    /**
     * 对象池模式
     */

    private ObjectPool<ThreadPool> pool;
    private AtomicLong processNo = new AtomicLong();
    private static final int DEFAULT_CAPACITY = 20;

    private void objectPoolLearn() {
        pool = new ObjectPool<ThreadPool>(5, 10) {
            protected ThreadPool create() {
                return new ThreadPool(processNo.incrementAndGet());
            }
        };
        ExecutorService executor = Executors.newFixedThreadPool(DEFAULT_CAPACITY);
        for (int i = 0; i < DEFAULT_CAPACITY; i++)
            executor.execute(new Task(pool, i));
        executor.shutdown();
    }


    /**
     * 享元模式
     */
    private  static  final int DEFAULT_COURIER_NUMBER = 10;
    private void flyWeightLearn(){
        Delivery deliveries[] = new Delivery[DEFAULT_COURIER_NUMBER];
        for (int i = 0; i < DEFAULT_COURIER_NUMBER; i++) {
            deliveries[i] = new Delivery(i);
        }
        deliveries[0].deliver(new Pack(0), new Destination(0));
        deliveries[1].deliver(new Pack(1), new Destination(1));
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }

    /**
     * 通过onTrimMemory来判断当前内存的状态 并进程处理
     * @param level
     */

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
}
