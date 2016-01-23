package com.ui.views;

import android.content.Context;

import com.ui.R;
import com.ui.boiled.BoiledView;
import com.ui.utils.AndroidUtils;

/**
 * name: Divider
 * desc:
 * date: 2015-06-14
 * author: David
 * Copyright (c) 2015 David Han
 */
public class Divider extends BoiledView {
    public Divider(Context context) {
        super(context);
    }

    @Override
    protected void init() {
        setBackground(AndroidUtils.getDrawable(getContext(),R.drawable.list_divider));
    }
}
