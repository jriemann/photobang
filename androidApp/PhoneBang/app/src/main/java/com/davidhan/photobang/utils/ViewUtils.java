package com.davidhan.photobang.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * name: ViewUtils
 * desc:
 * date: 2015-10-02
 * author: david
 * Copyright (c) 2015 David Han
 **/
public class ViewUtils {
    public static float dpToPx(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }
}
