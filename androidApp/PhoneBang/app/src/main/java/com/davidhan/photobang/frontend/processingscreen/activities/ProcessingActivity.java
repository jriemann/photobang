package com.davidhan.photobang.frontend.processingscreen.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.davidhan.photobang.R;
import com.davidhan.photobang.backend.io.Const;
import com.davidhan.photobang.backend.io.IOUtils;
import com.davidhan.photobang.frontend.resultscreen.activities.ResultsActivity;

import butterknife.ButterKnife;

/**
 * name: ResultsActivity
 * desc:
 * date: 2016-01-23
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class ProcessingActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processing);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        Intent i = new Intent(this,ResultsActivity.class);
        i.putExtra(Const.ALGO_OUTPUT, IOUtils.getDemoFiles());
        startActivity(i);
    }
}
