package com.ui.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

/**
 * name: AndroidUtils
 * desc:
 * date: 2015-06-14
 * author: David
 * Copyright (c) 2015 David Han
 */
public class AndroidUtils {
    public static Drawable getDrawable(Context context, int id){
        return ContextCompat.getDrawable(context, id);
    }
}
