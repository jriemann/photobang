package com.davidhan.photobang.frontend.resultscreen.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.davidhan.photobang.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * name: ResultGridItem
 * desc:
 * date: 2016-01-23
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class ResultGridItem extends RelativeLayout {
    @Bind(R.id.photo_item_border)
    View mBorder;
    @Bind(R.id.photo_item_check)
            View mCheck;
    @Bind(R.id.photo_img)
    ImageView mImage;
    private String photoObjectUID;
    boolean checked = false;
    public ResultGridItem(Context context) {
        super(context);
        initView();
    }

    public ResultGridItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ResultGridItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public ResultGridItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.view_result_photo_item, this);
        ButterKnife.bind(this);
        toggle(false);

    }
    public void setPhotoObjectUID(String uid){
        //mImage
        photoObjectUID = uid;
    }


    public void setImage(Bitmap bitmap){
        mImage.setImageBitmap(bitmap);
    }
//    @Override
//    public boolean onTouchEvent(MotionEvent e) {
//        switch (e.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                startAnimation(AnimUtils.getShrivelDown(getContext()));
//                return true;
//            case MotionEvent.ACTION_CANCEL:
//            case MotionEvent.ACTION_UP:
//                performClick();
//                clearAnimation();
//                startAnimation(AnimUtils.getScaleSpringUp(getContext()));
//
//        }
//
//        return super.onTouchEvent(e);
//    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec); // This is the key that will make the height equivalent to its width
    }
    public void toggle(boolean enabled){
        this.checked = enabled;
        mBorder.setVisibility(enabled ? VISIBLE : INVISIBLE);
        mCheck.setVisibility(enabled ? VISIBLE : INVISIBLE);

    }

    public String getPhotoObjectUID() {
        return photoObjectUID;
    }

    public boolean isChecked() {
        return checked;
    }


}
