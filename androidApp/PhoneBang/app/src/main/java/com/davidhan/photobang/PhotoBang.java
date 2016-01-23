package com.davidhan.photobang;

import android.app.Application;

import com.scopely.fontain.Fontain;

/**
 * name: WhereYouAt
 * desc:
 * date: 2015-09-28
 * author: david
 * Copyright (c) 2015 David Han
 **/
public class PhotoBang extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Fontain.init(this,"fonts","ProximaNova");

    }


}
