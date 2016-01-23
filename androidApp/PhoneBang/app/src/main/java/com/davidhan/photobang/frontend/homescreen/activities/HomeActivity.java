package com.davidhan.photobang.frontend.homescreen.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.davidhan.photobang.R;
import com.davidhan.photobang.frontend.resultscreen.activities.ResultsActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * name: HomeActivity
 * desc:
 * date: 2016-01-23
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class HomeActivity extends Activity {
    @Bind(R.id.home_find_bad_photos)
    Button mFindBadPhotosButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        mFindBadPhotosButton.setOnClickListener(onClick);
    }
    private View.OnClickListener onClick = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            if(v == mFindBadPhotosButton){
                launchFindAlgorithm();
            }
        }
    };

    private void launchFindAlgorithm() {
        startActivity(new Intent(this,ResultsActivity.class));
    }
}
