package com.ui.utils;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

/**
 * name: KeyboardUtils
 * desc:
 * date: 2015-06-16
 * author: David
 * Copyright (c) 2015 David Han
 */
public class KeyboardUtils {
    public static void hideSoftKeyboard(Activity activity) {

        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if(activity.getCurrentFocus()!= null) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }
}
