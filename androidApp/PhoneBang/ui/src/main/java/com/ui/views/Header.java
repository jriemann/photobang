package com.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ui.R;
import com.ui.boiled.BoiledLinearLayout;

/**
 * name: Header
 * desc:
 * date: 2015-06-14
 * author: David
 * Copyright (c) 2015 David Han
 */
public class Header extends BoiledLinearLayout {
    TextView mText;


    public Header(Context context, AttributeSet attrs) {

        super(context, attrs);

        initAttrs(attrs);
    }

    public Header(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttrs(attrs);
    }

    @Override
    protected void init() {
        inflate(getContext(), R.layout.header, this);
        mText = (TextView)findViewById(R.id.text);

    }
    private void initAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.Header, 0, 0);
        try {
            mText.setText(ta.getString(R.styleable.Header_header_text));
        } finally {
            ta.recycle();
        }
    }

}
