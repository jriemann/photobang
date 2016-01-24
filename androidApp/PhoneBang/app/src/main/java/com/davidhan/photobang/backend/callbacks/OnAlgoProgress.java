package com.davidhan.photobang.backend.callbacks;

import java.io.File;
import java.util.ArrayList;

/**
 * name: OnAlgoFinished
 * desc:
 * date: 2016-01-24
 * author: david
 * Copyright (c) 2016 David Han
 **/
public abstract class OnAlgoProgress {
    abstract public void onAlgoFinished(ArrayList<File> output);
    abstract public void onAlgoUpdated(int index,String status);
}
