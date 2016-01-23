package com.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ui.R;

/**
 * name: ToolbarActivity
 * desc:
 * date: 2015-06-13
 * author: David
 */
public class ToolbarActivity extends AppCompatActivity {
    protected Toolbar toolbar;
    protected ViewGroup mActivityContentHolder;
    protected ViewGroup mActivityContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setToolbarLayout();
        toolbar = (Toolbar) super.findViewById(R.id.toolbar);
        mActivityContentHolder = (ViewGroup) super.findViewById(R.id.activity_content_holder);
        setSupportActionBar(toolbar);

    }
    protected void setToolbarLayout(){
        setContentView(R.layout.toolbar_activity);
    }
    public void setContentLayout(int layoutId) {
        mActivityContent = (ViewGroup) LayoutInflater.from(this).inflate(layoutId, mActivityContentHolder, true);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }
    protected void initLayout(){}
    public void setBackButton(){
       setBackButton(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
    }
    public void setBackButton(int drawableId){
        toolbar.setNavigationIcon(drawableId);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public ViewGroup getContent(){
        return mActivityContent;
    }
    @Override
    public View findViewById(int id) {
        return mActivityContent.findViewById(id);
    }

}
