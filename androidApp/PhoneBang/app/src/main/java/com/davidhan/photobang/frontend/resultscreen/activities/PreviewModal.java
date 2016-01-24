package com.davidhan.photobang.frontend.resultscreen.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.davidhan.photobang.R;
import com.davidhan.photobang.backend.io.Const;
import com.davidhan.photobang.utils.ViewUtils;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * name: PreviewModal
 * desc:
 * date: 2016-01-23
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class PreviewModal extends Activity {
    @Bind(R.id.preview_image)
    ImageView mPreviewImage;
    Bitmap largePhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscren_preview_modal);
        ButterKnife.bind(this);
        initIntent();
        initWindow();
        initViews();
    }

    private void initIntent() {
        if(getIntent().getExtras() != null){
            if(getIntent().getExtras().getSerializable(Const.FILE) != null){
                File file = (File)getIntent().getExtras().getSerializable(Const.FILE);
                largePhoto  = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(file.getPath()), 1080, 1920);
                mPreviewImage.setImageBitmap(largePhoto);
            }
        }

    }

    private void initWindow() {


        getWindow().setBackgroundDrawable(null);

    }

    private void initViews() {
        if(largePhoto != null) {
           float ratio =  (float)largePhoto.getHeight()/(float)largePhoto.getWidth();
            DisplayMetrics metrics = new DisplayMetrics();
            int sideMarginsCombined  = (int)ViewUtils.dpToPx(48,this);
            getWindowManager().getDefaultDisplay().getMetrics(metrics);

            ViewGroup.LayoutParams params =     mPreviewImage.getLayoutParams();


            params.width = Math.min((int) ViewUtils.dpToPx(420, this), metrics.widthPixels - sideMarginsCombined);
            params.height = (int)(( params.width*ratio));
            mPreviewImage.setLayoutParams(params);

        }
        mPreviewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}
