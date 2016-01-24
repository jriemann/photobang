package com.davidhan.photobang.frontend.processingscreen.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.Environment;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.content.res.AssetManager;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.photo.Photo;
import static org.opencv.core.Core.convertScaleAbs;


import com.davidhan.photobang.R;
import com.davidhan.photobang.backend.algo.Algorithm;
import com.davidhan.photobang.backend.base.PhotoObject;

import java.io.InputStream;
import java.util.ArrayList;

import butterknife.Bind;


import com.davidhan.photobang.R;
import com.davidhan.photobang.backend.io.Const;
import com.davidhan.photobang.backend.io.IOUtils;
import com.davidhan.photobang.frontend.resultscreen.activities.ResultsActivity;
import com.davidhan.photobang.utils.AnimUtils;

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
    @Bind(R.id.processing_gear_1)
    ImageView mGear1;
    @Bind(R.id.processing_gear_2)
    ImageView mGear2;
    private final static String TAG = "ProcessingActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processing);
        ButterKnife.bind(this);

        Log.i(TAG, "Init with");
        double before = System.currentTimeMillis();
        //getImagesFromSd();

        ArrayList<File> test_files = new ArrayList<File>()
        {{
                add(new File("blurry_3.jpg"));
                add(new File("blurry_5.jpg"));
                add(new File("SCREENSHOT_face_2.jpg"));
                add(new File("test.jpg"));
                add(new File("test2.jpg"));
                add(new File("black.jpg"));
            }};

        Log.i(TAG, "Time elapsed: " + (System.currentTimeMillis() - before) + " ms.");
        initView();
        initAlgo();
    }
    private void initAlgo(){
        if (!OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_1_0, this, new BaseLoaderCallback(this) {
            @Override
            public void onManagerConnected(int status) {
                switch (status) {
                    case LoaderCallbackInterface.SUCCESS:
                    {
                        Log.i(TAG, "OpenCV loaded successfully");
                        // Create and set View
//                    mView = new puzzle15View(mAppContext);
//                    setContentView(mView);
                        //m = new Mat();
                        onAlgoInited();


                    } break;
                    default:
                    {
                        super.onManagerConnected(status);
                    } break;
                }
            }

        }))
        {
            Log.e(TAG, "Cannot connect to OpenCV Manager");
        }
    }

    private void onAlgoInited() {
        Algorithm.getBadPhotos(IOUtils.getDemoFiles(),false);
    }
    private void initView() {

    }
    private void launchResults(){
        Intent i = new Intent(this,ResultsActivity.class);
        i.putExtra(Const.ALGO_OUTPUT, IOUtils.getDemoFiles());


        mGear1.startAnimation(AnimUtils.getSpinClockWise(this));
        mGear2.startAnimation(AnimUtils.getSpinConterClockwise(this));
      //  startActivity(i);
    }

    

}
