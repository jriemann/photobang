package com.ui.views.viewgroups;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ui.utils.KeyboardUtils;

/**
 * name: KeyInputLinearLayout
 * desc: A linear layout that holds an editext or whatever that automatically handles
 * the tap outside to close behavior.
 * date: 2015-06-16
 * author: David
 * Copyright (c) 2015 David Han
 */
public class KeyInputLinearLayout extends LinearLayout{
    public KeyInputLinearLayout(Context context) {
        super(context);
        init();
    }

    public KeyInputLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public KeyInputLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        inflate(getContext(), com.ui.R.layout.keyinputlinearlayout_view,this);
        final LinearLayout me = this;

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("me", String.valueOf(view instanceof EditText));
                KeyboardUtils.hideSoftKeyboard((Activity) getContext());
            }
        });
    }
}
