package com.davidhan.photobang.backend.algo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;
import android.util.Log;

import com.davidhan.photobang.backend.base.PhotoObject;
import com.davidhan.photobang.backend.callbacks.OnAlgoProgress;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.ArrayList;

import static org.opencv.core.Core.convertScaleAbs;

/**
 * Created by shreymahendru on 2016-01-24.
 */
public class Algorithm {

    private final static String TAG = "Processor";
    public Mat imgMAT;
   public Mat greyMAT;

    public static void getBadPhotos(final ArrayList<File> all_files, final OnAlgoProgress onAlgoProgress, final boolean detectScreenshots) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<File> bad_files = new ArrayList<File>();
                int count = 0;
                for (File file : all_files) {
                    count++;
                    if (isBlurry(file) || (detectScreenshots && isScreenshot(file))) {
                        bad_files.add(file);
                        onAlgoProgress.onAlgoUpdated(count, "BAD");
                    } else {
                        onAlgoProgress.onAlgoUpdated(count, "GOOD");
                    }
                }
                onAlgoProgress.onAlgoFinished(bad_files);
            }
        });

    }

    public static int deletePhotos(ArrayList<PhotoObject> photos) {
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
    public static ArrayList<Long> getTotalFileSize(ArrayList<File> file_list) {
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
    public static boolean isScreenshot(File file) {
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
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static boolean isBlurry(File file) {
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//
        Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
        ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(file.getPath()), 2000, 2000);
        Mat imgMAT = new Mat(bitmap.getWidth(), bitmap.getHeight(), CvType.CV_8UC1);
        //imgMAT = new Mat();

        //Log.i(TAG, "Mat declared");
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        //Log.i(TAG, "width = " + bitmap.getWidth() + " height = " + bitmap.getHeight());
        Utils.bitmapToMat(bitmap, imgMAT);

        Mat greyMAT = new Mat(height, width, CvType.CV_8UC1);
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

            return true;
        }
        else
        {
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
