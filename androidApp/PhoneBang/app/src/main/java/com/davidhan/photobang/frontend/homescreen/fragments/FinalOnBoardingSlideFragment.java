package com.davidhan.photobang.frontend.homescreen.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.davidhan.photobang.R;
import com.davidhan.photobang.frontend.homescreen.activities.HomeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * name: OnBoardingSlideFragment
 * desc:
 * date: 2016-01-23
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class FinalOnBoardingSlideFragment extends Fragment {
    private int position;
    @Bind(R.id.onboarding_slide_img)
    ImageView mImage;
    @Bind(R.id.home_find_bad_photos)
    Button mButton;

    ViewGroup mRootView;

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_onboarding_final_slide, container, false);


        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.bind(this, mRootView);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity) getActivity()).launchFindAlgorithm();
            }
        });

    }




}
