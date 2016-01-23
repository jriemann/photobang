package com.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ui.R;

/**
 * name: ActionItem
 * desc:
 * date: 2015-06-20
 * author: David
 * Copyright (c) 2015 David Han
 */
public class ActionItem extends LinearLayout {
    TextView mTitle;
    TextView mValue;
    ImageView mChevron;
    public ActionItem(Context context) {
        super(context);
        inflateLayout();
        init();
    }

    public ActionItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        inflateLayout();
        init();
        initAttrs(attrs);
    }

    public ActionItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        inflateLayout();
        init();
        initAttrs(attrs);
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ActionItem, 0, 0);
        try {


            boolean showChevron = ta.getBoolean(R.styleable.ActionItem_chevron,true);

            mTitle.setText(ta.getString(R.styleable.ActionItem_title_text));
            mValue.setText(ta.getString(R.styleable.ActionItem_value_text));
            mChevron.setVisibility(showChevron?VISIBLE:GONE);
        } finally {
            ta.recycle();
        }
    }
    private void init() {

        mTitle = (TextView)findViewById(R.id.title);
        mValue = (TextView)findViewById(R.id.value);
        mChevron = (ImageView)findViewById(R.id.chevron);
    }
    private void inflateLayout(){
        inflate(getContext(), R.layout.action_item,this);
    }
    public void showChevron(boolean show){
        mChevron.setVisibility(show?VISIBLE:GONE);
    }
    public void setTitle(String title){
        mTitle.setText(title);
    }
    public void setValue(String value){
        mValue.setText(value);
    }
}
