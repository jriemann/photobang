package com.davidhan.photobang.backend.base;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;

import java.io.File;

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
    private File file;
    private boolean selected;
    private Bitmap thumbnail;
    private Bitmap fullImage;
    public PhotoObject(File file){
        this.file = file;
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
            thumbnail = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(file.getPath()), 128, 128);
        }
        return thumbnail;
    }

    public Bitmap getFullImage() {
        if(fullImage == null){
            fullImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(file.getPath()), 1080 , 1920);
        }
        return fullImage;
    }
}
