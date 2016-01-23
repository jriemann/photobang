package com.ui.model;

import com.ui.listeners.Callback;

import java.io.Serializable;

/**
 * name: DialogItem
 * desc:
 * date: 2015-06-26
 * author: David
 * Copyright (c) 2015 David Han
 */
public class DialogItem implements Serializable {
    public String title;
    public String value;
    public Callback callback;
    public DialogItem(String title, String value, Callback callback){
        this.title = title;
        this.value = value;
        this.callback = callback;
    }
}