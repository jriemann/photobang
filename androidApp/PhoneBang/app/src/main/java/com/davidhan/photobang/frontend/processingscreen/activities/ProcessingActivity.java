package com.davidhan.photobang.frontend.processingscreen.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.davidhan.photobang.R;
import com.davidhan.photobang.backend.algo.Algorithm;
import com.davidhan.photobang.backend.callbacks.OnAlgoProgress;
import com.davidhan.photobang.backend.io.Const;
import com.davidhan.photobang.backend.io.IOUtils;
import com.davidhan.photobang.frontend.resultscreen.activities.ResultsActivity;
import com.davidhan.photobang.utils.AnimUtils;
import com.ui.model.SerializableArrayList;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

import java.io.File;
import java.util.ArrayList;

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
    @Bind(R.id.processing_text)
    TextView mProgressText;
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
        ArrayList<File> input = IOUtils.getDemoFiles();
        final int total = input.size();
        Algorithm.getBadPhotos(input, new OnAlgoProgress() {
            @Override
            public void onAlgoFinished(final ArrayList<File> output) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mProgressText.setText("Finished. Bad count: " + output.size());
                        launchResults(output);
                    }
                });

            }

            @Override
            public void onAlgoUpdated(final int index, String status) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mProgressText.setText("Processing "+ index+ "/"+total+ " images...");
                    }
                });
            }

        }, getIntent().getExtras().getBoolean(Const.SCREENSHOT));
    }
    private void initView() {
        mGear1.startAnimation(AnimUtils.getSpinClockWise(this));
        mGear2.startAnimation(AnimUtils.getSpinConterClockwise(this));
    }
    private void launchResults(ArrayList<File>arrayListOutput){
        Intent i = new Intent(this,ResultsActivity.class);
        SerializableArrayList<File> serializedOutput = new SerializableArrayList<>();
        serializedOutput.addAll(arrayListOutput);
        i.putExtra(Const.ALGO_OUTPUT, serializedOutput);
        startActivity(i);
    }

    

}
