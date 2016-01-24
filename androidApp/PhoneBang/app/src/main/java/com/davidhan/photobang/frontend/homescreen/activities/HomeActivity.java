package com.davidhan.photobang.frontend.homescreen.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.davidhan.photobang.R;
import com.davidhan.photobang.frontend.homescreen.HomeViewPagerAdapter;
import com.davidhan.photobang.frontend.processingscreen.activities.ProcessingActivity;

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
    @Bind(R.id.home_viewpager)
    ViewPager mViewPager;
    FragmentPagerAdapter mFragmentPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {

        mFragmentPager = new HomeViewPagerAdapter(getFragmentManager());
        mViewPager.setAdapter(mFragmentPager);
       // launchFindAlgorithm();

    }
    private View.OnClickListener onClick = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

        }
    };
    public void swipeNext(){
        mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1,true);
    }
    public void launchFindAlgorithm() {
        startActivity(new Intent(this,ProcessingActivity.class));
    }
}
