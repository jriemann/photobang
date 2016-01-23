package com.davidhan.photobang.frontend.base;

import android.os.Bundle;

import com.davidhan.photobang.PhotoBang;
import com.ui.activities.ToolbarActivity;

/**
 * name: BaseToolbarActivity
 * desc:
 * date: 2015-10-04
 * author: david
 * Copyright (c) 2015 David Han
 **/
public class BaseToolbarActivity extends ToolbarActivity {
    protected PhotoBang application;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        application = (PhotoBang)getApplication();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
