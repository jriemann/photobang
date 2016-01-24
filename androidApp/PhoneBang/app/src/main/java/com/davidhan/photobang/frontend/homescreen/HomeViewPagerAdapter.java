package com.davidhan.photobang.frontend.homescreen;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.davidhan.photobang.frontend.homescreen.fragments.FinalOnBoardingSlideFragment;
import com.davidhan.photobang.frontend.homescreen.fragments.OnBoardingSlideFragment;

/**
 * name: HomeViewPagerAdapter
 * desc:
 * date: 2016-01-23
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class HomeViewPagerAdapter extends FragmentPagerAdapter {
    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position < 3) {
            OnBoardingSlideFragment fragment  = new OnBoardingSlideFragment();
            fragment.setPosition(position);
            return fragment;
        }else{
            return new FinalOnBoardingSlideFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
