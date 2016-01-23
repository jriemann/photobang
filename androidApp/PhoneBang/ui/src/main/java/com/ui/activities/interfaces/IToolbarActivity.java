package com.ui.activities.interfaces;

import android.support.v7.widget.Toolbar;

/**
 * name: IToolbarActivity
 * desc:
 * date: 2015-06-15
 * author: David
 * Copyright (c) 2015 David Han
 */
public interface IToolbarActivity {
    Toolbar getToolbar();
    void showBackButton(boolean visible);
}
