package com.davidhan.photobang.frontend.processingscreen.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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

import com.davidhan.photobang.R;
import com.davidhan.photobang.backend.base.PhotoObject;

import java.io.InputStream;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static org.opencv.core.Core.convertScaleAbs;
import static org.opencv.core.Core.log;

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
    Mat greyMAT;

    @Bind(R.id.results_gridview)
    GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
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
        ArrayList<File> blurry_files = getBadPhotos(test_files, true); // Change this later
        ArrayList<Long> file_sizes = getTotalFileSize(test_files);
        Log.i(TAG, "Time elapsed: " + (System.currentTimeMillis() - before) + " ms.");

    }
    /*private void getImagesFromSd()
    {
        String ExternalStorageDirectoryPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String targetpath = ExternalStorageDirectoryPath + "/test/";
        Toast.makeText(getApplicationContext(), targetpath, Toast.LENGTH_LONG).show();
        File targetdir=new File(targetpath);
        File[] files = targetdir.listFiles();
        for (File file :files)
        {
            boolean result = initView(file.getAbsolutePath());
            Log.i(TAG, "Picture Results ="+ result );
        }
    }*/

    private ArrayList<File> getBadPhotos(ArrayList<File> all_files, boolean detectScreenshots) {
        ArrayList<File> bad_files = new ArrayList<File>();
        for (File file :all_files)
        {
            if(isBlurry(file) || (detectScreenshots && isScreenshot(file))) {
                bad_files.add(file);
                Log.i(TAG, file + " is blurry or a screenshot");
            }
        }

        return bad_files;
    }

    private int deletePhotos(ArrayList<PhotoObject> photos) {
        String filename;
        File file;
        int count = 0;
        for (PhotoObject photo :photos)
        {
            filename = photo.getFile().getAbsolutePath();
            file = new File(filename);
            if (!file.delete()){
                count += 1;
                Log.i(TAG, "File deletion failed on " + filename);
                return -1;
            }
        }
        Log.i(TAG, "File deletion completed successfully! Deleted " + count + " files.");
        return count;
    }

    /**
     * Take a list of file objects, and return a list of equal size
     * containing the corresponding file sizes.
     * @param file_list
     * @return
     */
    private ArrayList<Long> getTotalFileSize(ArrayList<File> file_list) {
        ArrayList<Long> file_sizes = new ArrayList<Long>();
        long size;
        String name;
        for (File file :file_list) {
            if (file.exists()) {
                size = ((long) file.length());
                name = file.getAbsolutePath();
                Log.i(TAG, "File name is: " + name + " megabytes");
                file_sizes.add(size);
                Log.i(TAG, "File size is: " + size + " megabytes");
            } else {
                Log.i(TAG, file.getAbsolutePath() + " was not found");
            }
        }
        return file_sizes;

    }
    private boolean isScreenshot(File file) {
        String filename = file.getName();
        filename=filename.toUpperCase();
        if (filename.length()< 9 )
        {
            Log.i(TAG, filename + "is a screenshot: false.");
            return false;
        }
        String slice = filename.substring(0, 10);
        Log.i(TAG, filename + "is a screenshot: " + slice.equals("SCREENSHOT") + ". Slice is: " + slice);
        return (slice.equals("SCREENSHOT"));
    }


    private boolean isBlurry(File file) {
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        AssetManager assetManager = getAssets();
        InputStream is = null;
//        if (!OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_1_0, this, mOpenCVCallBack))
//        {
//            Log.e(TAG, "Cannot connect to OpenCV Manager");
//        }

        try {
            is = assetManager.open(file.getName()) ;//.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }


        Bitmap bitmap = BitmapFactory.decodeStream(is);
        Log.i(TAG, "Got the bitmap");

        imgMAT = new Mat(bitmap.getWidth(), bitmap.getHeight(), CvType.CV_8UC1);
        //imgMAT = new Mat();

        //Log.i(TAG, "Mat declared");
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        //Log.i(TAG, "width = " + bitmap.getWidth() + " height = " + bitmap.getHeight());
        Utils.bitmapToMat(bitmap, imgMAT);

        greyMAT = new Mat(height, width, CvType.CV_8UC1);
        Imgproc.cvtColor(imgMAT, greyMAT, Imgproc.COLOR_RGBA2GRAY);
        //Log.i(TAG, "Converted to grey.");

        //int sharpness = getSharpness(greyMAT, width, height);
        //Log.i(TAG, "sharpess: " + sharpness);
        Imgproc.Laplacian(greyMAT, greyMAT, greyMAT.depth());
        //Log.i(TAG, "Converted to Laplacian.");

        convertScaleAbs(greyMAT, greyMAT);
        //Log.i(TAG, "Converted to scale abs.");


        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inDither = true;
        opt.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap image = bitmap;
        // Bitmap image = BitmapFactory.decodeByteArray(im, 0, im.length);
        int l = CvType.CV_8UC1; //8-bit grey scale image
        Mat matImage = new Mat();
        Utils.bitmapToMat(image, matImage);
        Mat matImageGrey = new Mat();
        Imgproc.cvtColor(matImage, matImageGrey, Imgproc.COLOR_BGR2GRAY);

        Bitmap destImage;
        destImage = Bitmap.createBitmap(image);
        Mat dst2 = new Mat();
        Utils.bitmapToMat(destImage, dst2);
        Mat laplacianImage = new Mat();
        dst2.convertTo(laplacianImage, l);
        Imgproc.Laplacian(matImageGrey, laplacianImage, CvType.CV_8U);
        Mat laplacianImage8bit = new Mat();
        laplacianImage.convertTo(laplacianImage8bit, l);



        Bitmap bmp = Bitmap.createBitmap(greyMAT.cols(),
                greyMAT.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(greyMAT, bmp);

        //Imgcodecs.imwrite(cacheFile.getAbsolutePath(), tmp);
        //File dir = getFilesDir();
        //Imgcodecs.imwrite(dir.toString() + "/test2.jpg", greyMAT);
        //Imgcodecs.imwrite(dir.toString() + "test2.jpg", greyMAT);
        //double[] colors = greyMAT.get(0,0);
        int[] pixels = new int[bmp.getHeight() * bmp.getWidth()];
        //double max = 0;
        //double [] colors = greyMAT.get(50, 100);
        //Log.i(TAG, "--->" + colors[0]);
        /*for(int i=0; i<10; i++){

            for(int j=0; i<10; j++){
                colors = greyMAT.get(i, j);
                colors = greyMAT.get(i, j);

                if (colors[0] > max) {
                    Log.i(TAG, "--->" + colors[0]);

                    max = colors[0];
                    //Log.i(TAG, "--->" + colors[0]);

                }
            }
        } */
        bmp.getPixels(pixels, 0, bmp.getWidth(), 0, 0, bmp.getWidth(),
                bmp.getHeight());

        int maxLap = -16777216;

        for (int i = 0; i < pixels.length; i++) {
            if (pixels[i] > maxLap)
                maxLap = pixels[i];
        }

        int soglia = -6118750;
        //Log.i(TAG, "WHAAAT: " + (maxLap < soglia) );
        //Log.i(TAG, "maxLap: " + maxLap);
        if (maxLap < soglia || maxLap == soglia) {
            Log.i(TAG, "blur image");
            return true;
        }
        else
        {
            Log.i(TAG, "image good");
            return false;
        }

        //Log.i(TAG, "Max value in array is: " + max);
        //if (maxLap > 200) {
        //    Log.i(TAG, "Image is in focus " + maxLap);
        //} else {
        //    Log.i(TAG, "Image is blurry: " + maxLap );
        //}
    }
    
}
