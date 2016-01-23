package com.ui.activities.interfaces;

import android.support.v7.widget.Toolbar;

/**
 * name: IMultifragmentActivity
 * desc:
 * date: 2015-06-15
 * author: David
 * Copyright (c) 2015 David Han
 */
public interface IMultifragmentActivity {
    Toolbar getToolbar();
    void showBackButton(boolean visible);
}
