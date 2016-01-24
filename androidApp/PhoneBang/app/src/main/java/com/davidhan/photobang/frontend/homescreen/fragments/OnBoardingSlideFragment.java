package com.davidhan.photobang.frontend.homescreen.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
public class OnBoardingSlideFragment extends Fragment {
    private int position;
    @Bind(R.id.onboarding_slide_img)
    ImageView mImage;
    @Bind(R.id.onboarding_slide_next)
    Button mNext;
    @Bind(R.id.onboarding_slide_text)
    TextView mText;
    ViewGroup mRootView;

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_onboarding_slide, container, false);


        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ButterKnife.bind(this, mRootView);
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity) getActivity()).swipeNext();
            }
        });
        switch (position){
            case 0:
                mText.setText("Photos can take up a lot of space on your phone...");
                mImage.setImageResource(R.drawable.pic_photos_in_sd);
                break;
            case 1:
                mText.setText("Prune will fetch you all of the bad photos on your phone...");
                mImage.setImageResource(R.drawable.pic_science);
                break;
            case 2:
                mText.setText("So that you can clean out your phone the smart way.");
                mImage.setImageResource(R.drawable.pic_vacuum);
                break;
        }
        mNext.setText("Next ("+(3-position)+")");
    }

    public void setImageRes(int resId) {
        mImage.setImageResource(resId);
    }
    public void setText(String text){
        mText.setText(text);
    }
    public void setNextOnClickListener(View.OnClickListener onClick){
        mNext.setOnClickListener(onClick);
    }
}
