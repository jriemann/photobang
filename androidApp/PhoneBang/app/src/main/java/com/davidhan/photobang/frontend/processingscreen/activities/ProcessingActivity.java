package com.davidhan.photobang.frontend.processingscreen.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import com.davidhan.photobang.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * name: ResultsActivity
 * desc:
 * date: 2016-01-23
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class ProcessingActivity extends Activity{
    @Bind(R.id.results_gridview)
    GridView mGridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

    }
}
