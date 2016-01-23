package com.davidhan.photobang.frontend.processingscreen.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.content.res.AssetManager;
import java.io.IOException;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import com.davidhan.photobang.R;

import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * name: ResultsActivity
 * desc:
 * date: 2016-01-23
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class ProcessingActivity extends Activity{
    private final static String TAG = "Processor";

    Mat imgMAT;

    @Bind(R.id.results_gridview)
    GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        initView();
    }



    private void initView() {
        AssetManager assetManager = getAssets();
        InputStream is = null;
//        if (!OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_1_0, this, mOpenCVCallBack))
//        {
//            Log.e(TAG, "Cannot connect to OpenCV Manager");
//        }

        try {
            is = assetManager.open("test.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }


        Bitmap bitmap = BitmapFactory.decodeStream(is);
        Log.i(TAG, "I am in it");

        //Mat imgMAT = new Mat(bitmap.getWidth(), bitmap.getHeight(), CvType.CV_8UC1);
        imgMAT = new Mat();

        Log.i(TAG, "Mat declared");

        Log.i(TAG, "width = "+ bitmap.getWidth() + " height = " + bitmap.getHeight() );
        Utils.bitmapToMat(bitmap, imgMAT);




    }
}
