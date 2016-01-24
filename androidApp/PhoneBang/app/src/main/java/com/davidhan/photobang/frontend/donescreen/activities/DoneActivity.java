package com.davidhan.photobang.frontend.donescreen.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.davidhan.photobang.R;
import com.davidhan.photobang.frontend.homescreen.activities.HomeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * name: DoneActivity
 * desc:
 * date: 2016-01-23
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class DoneActivity extends Activity {
    @Bind(R.id.onboarding_slide_img)
    ImageView mImage;
    @Bind(R.id.onboarding_slide_text)
    TextView mText;
    @Bind(R.id.home_find_bad_photos)
    Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_onboarding_final_slide);
        ButterKnife.bind(this);
        mText.setText("Photos were successfully deleted.");
        mButton.setText("Done");
        mImage.setImageResource(R.drawable.pic_picture);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               goHome();
            }
        });

    }

    @Override
    public void onBackPressed() {
        goHome();
    }

    private void goHome(){
        Intent i = new Intent(DoneActivity.this, HomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
