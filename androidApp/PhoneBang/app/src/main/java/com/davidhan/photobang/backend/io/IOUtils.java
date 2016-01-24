package com.davidhan.photobang.backend.io;

import android.os.Environment;
import android.util.Log;

import com.ui.model.SerializableArrayList;

import java.io.File;
import java.util.Collections;

/**
 * name: IOUtils
 * desc:
 * date: 2016-01-23
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class IOUtils {
    public static File getDemoAlbumStorageDir() {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "UofTHacks2016");
        if (!file.mkdirs()) {
            Log.e("Directory error", "Directory not created");
        }
        return file;
    }
    public static SerializableArrayList<File> getDemoFiles(){
        File storDir = getDemoAlbumStorageDir();
        SerializableArrayList<File> output = new SerializableArrayList<>();
        Collections.addAll(output, storDir.listFiles());
        return output;
    }

}
