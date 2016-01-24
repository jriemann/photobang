package com.davidhan.photobang.frontend.processingscreen.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.davidhan.photobang.R;
import com.davidhan.photobang.backend.io.Const;
import com.davidhan.photobang.backend.io.IOUtils;
import com.davidhan.photobang.frontend.resultscreen.activities.ResultsActivity;
import com.davidhan.photobang.utils.AnimUtils;

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
    @Bind(R.id.processing_gear_1)
    ImageView mGear1;
    @Bind(R.id.processing_gear_2)
    ImageView mGear2;
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


        mGear1.startAnimation(AnimUtils.getSpinClockWise(this));
        mGear2.startAnimation(AnimUtils.getSpinConterClockwise(this));
      //  startActivity(i);
    }
}
