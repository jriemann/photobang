package com.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.ui.R;
import com.ui.boiled.BoiledLinearLayout;

/**
 * name: PermaSnackbar
 * desc:
 * date: 2015-06-21
 * author: David
 * Copyright (c) 2015 David Han
 */
public class PermaSnackbar extends BoiledLinearLayout {

    public PermaSnackbar(Context context) {
        super(context);
    }

    public PermaSnackbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PermaSnackbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        inflate(getContext(), R.layout.perma_snackbar,this);
        super.init();
    }
    public void show(ViewGroup parent){
       // setLayoutGr(Gravity.BOTTOM);
        parent.addView(this);

    }
}
