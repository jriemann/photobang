package com.ui.fragments;

import android.app.Activity;
import android.app.Fragment;

import com.ui.activities.interfaces.IMultifragmentActivity;

/**
 * name: MultiFragActiFragment
 * desc: A Fragment to be used on MultiFragmentActivity.
 * date: 2015-06-15
 * author: David
 * Copyright (c) 2015 David Han
 */
public class MultiFragActiFragment extends Fragment{
    protected IMultifragmentActivity activity;
    @Override
    public void onAttach(Activity activity) {
        this.activity = (IMultifragmentActivity)activity;
        super.onAttach(activity);
    }
}
