package com.davidhan.photobang.utils;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.davidhan.photobang.R;

/**
 * name: AnimUtils
 * desc:
 * date: 2016-01-23
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class AnimUtils {
    public static Animation getShrivelDown(Context context){
        return AnimationUtils.loadAnimation(context, R.anim.shrivel_down);
    }
    public static Animation getScaleSpringUp(Context context){
        return AnimationUtils.loadAnimation(context, R.anim.scale_spring_up);
    }
}
