package com.ui.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * name: CommonDialogs
 * desc:
 * date: 2015-06-27
 * author: David
 * Copyright (c) 2015 David Han
 */
public class CommonDialogs {
    public static AlertDialog.Builder getNotifyDialog(Context context){
        return new AlertDialog.Builder(context).setPositiveButton("Got it",null);
    }
    public static AlertDialog.Builder getConfirmDialog(Context context, DialogInterface.OnClickListener onPositiveClick){
        return new AlertDialog.Builder(context)
                .setPositiveButton("Confirm", onPositiveClick)
                .setNegativeButton("Deny",null);
    }
}
