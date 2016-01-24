package com.davidhan.photobang.frontend.resultscreen.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.davidhan.photobang.R;
import com.davidhan.photobang.backend.io.Const;
import com.davidhan.photobang.frontend.donescreen.activities.DoneActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * name: PreviewModal
 * desc:
 * date: 2016-01-23
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class ConfirmDeleteModal extends Activity {
    @Bind(R.id.confirm_delete_cancel)
    Button mCancelButton;
    @Bind(R.id.confirm_delete_confirm)
    Button mConfirmButton;
    @Bind(R.id.confirm_delete_text)
    TextView mDeleteText;
    int totalAmnt;
    int selectedAmnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_delete_modal);
        ButterKnife.bind(this);
        initIntent();
        initWindow();
        initViews();
    }

    private void initIntent() {
        if(getIntent().getExtras() != null){
            if(getIntent().getExtras().get(Const.TOTAL_AMNT) != null){
                totalAmnt = (Integer) getIntent().getExtras().get(Const.TOTAL_AMNT);
            }
            if(getIntent().getExtras().get(Const.SELECTED_AMNT) != null){
                selectedAmnt = (Integer) getIntent().getExtras().get(Const.SELECTED_AMNT);
            }
        }
    }

    private void initWindow() {

    }

    private void initViews() {
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(ConfirmDeleteModal.this, DoneActivity.class));
                finish();
            }
        });
        mDeleteText.setText("Are you sure you want to delete "+selectedAmnt+" pictures?");
    }

}
