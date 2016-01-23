package com.ui.boiled;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * name: BoildDivider
 * desc:
 * date: 2015-06-14
 * author: David
 * Copyright (c) 2015 David Han
 */
public class BoiledView extends View {
    public BoiledView(Context context) {
        super(context);
        init();
    }

    public BoiledView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }



    public BoiledView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }
    protected void init() {

    }
}
