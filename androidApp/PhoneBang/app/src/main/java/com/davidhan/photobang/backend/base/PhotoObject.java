package com.davidhan.photobang.backend.base;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;

import java.io.File;
import java.util.UUID;

/**
 * name: PhotoObject
 * desc:
 * date: 2016-01-23
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class PhotoObject {

    /**
     * Path of the file
     */
    private String uid;
    private File file;
    private boolean selected;
    private Bitmap thumbnail;
    private Bitmap fullImage;
    public PhotoObject(File file){
        uid = UUID.randomUUID().toString();
        this.file = file;
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                getThumbnail();
//            }
//        };
//        new Thread(runnable).start();
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public File getFile() {
        return file;
    }

    public Bitmap getThumbnail() {
        if(thumbnail == null){
            thumbnail = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(file.getPath()), 144, 144);
        }
        return thumbnail;
    }

    public Bitmap getFullImage() {
        if(fullImage == null){
            fullImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(file.getPath()), 1080 , 1920);
        }
        return fullImage;
    }

    public String getUID() {
        return uid;
    }
}
