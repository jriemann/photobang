package com.ui.containers;

import android.content.Context;
import android.view.View;

import com.ui.boiled.BoiledLinearLayout;
import com.ui.views.Divider;

/**
 * name: ListContainer
 * desc:
 * date: 2015-06-14
 * author: David
 * Copyright (c) 2015 David Han
 */
public class ListContainer extends BoiledLinearLayout {
    public ListContainer(Context context) {
        super(context);
    }

    public void addItem(View view){
        if(getChildCount() > 0){
            addView(new Divider(getContext()));
        }
        addView(view);
    }
}
