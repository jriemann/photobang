package com.ui.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ui.R;
import com.ui.activities.interfaces.IMultifragmentActivity;

import java.util.List;


/**
 * name: MultiFragmentActivity
 * desc:
 * date: 2015-06-14
 * author: David
 * Copyright (c) 2015 David Han
 */
public class MultiFragmentActivity extends com.ui.activities.ToolbarActivity implements IMultifragmentActivity{
    protected MultiFragmentPagerAdapter mAdapter;
    protected List<Fragment> mFragmentList;
    protected ViewPager mPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout();
        mPager = (ViewPager) findViewById(R.id.pager);
        initFragmentsList();
        mAdapter = new MultiFragmentPagerAdapter(getFragmentManager());

        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
            @Override
            public void onPageSelected(int position){

                invalidateOptionsMenu();
            }



        });
        mPager.setAdapter(mAdapter);
    }

    @Override
    protected void initLayout() {
        setContentLayout(R.layout.multifragment_activity);
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void showBackButton(boolean visible) {
        if(visible) {
            super.setBackButton();
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mPager.getCurrentItem() > 0) mPager.setCurrentItem(0, true);
                }
            });
        }else{
            toolbar.setNavigationIcon(null);
        }
    }


    private class MultiFragmentPagerAdapter extends FragmentPagerAdapter {
        public MultiFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return mFragmentList.get(i);
        }

        @Override
        public int getCount() {
            return  mFragmentList.size();
        }
    }
    protected void initFragmentsList(){

    }
}
