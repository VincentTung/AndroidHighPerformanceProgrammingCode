package com.vincent.high.performance.other;


import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import com.vincent.high.performance.activity.MainActivity;
import com.vincent.high.performance.entity.News;

/**
 * java8 静态接口方法
 */
public interface OnNewsSelected {
    void onNewsClick(News news);
    default  void onNewsLongClick(Context context,News news){

        Intent intent  = new Intent(context,MainActivity.class);
        intent.putExtra("news", (Parcelable) news);
        context.startActivity(intent);

    }
}
