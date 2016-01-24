package com.davidhan.photobang.frontend.resultscreen.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.davidhan.photobang.backend.base.PhotoObject;
import com.davidhan.photobang.frontend.resultscreen.activities.ResultsActivity;
import com.davidhan.photobang.frontend.resultscreen.views.ResultGridItem;

/**
 * name: ResultsGridViewAdapter
 * desc:
 * date: 2016-01-23
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class ResultsGridViewAdapter extends BaseAdapter {
    Context context;
    public ResultsActivity mActivity;

    public  ResultsGridViewAdapter(Context context){
        this.context = context;
        this.mActivity = (ResultsActivity)context;
    }

    @Override
    public int getCount() {
        return mActivity.getPhotoObjects().size();
    }

    @Override
    public PhotoObject getItem(int position) {
        return mActivity.getPhotoObjects().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ResultsGridViewItemViewHolder holder;
        if(convertView == null || convertView.getTag() == null) {
            convertView = new ResultGridItem(context);

           holder = new ResultsGridViewItemViewHolder();
          //  holder.thumbnail = (ImageView) convertView.findViewById(R.id.grid_item_thumbnail);
            convertView.setTag(holder);
        }else{
            holder = (ResultsGridViewItemViewHolder) convertView.getTag();
        }
        ((ResultGridItem)convertView).setPhotoObjectUID(getItem(position).getUID());
        ((ResultGridItem)convertView).setImage(getItem(position).getThumbnail());
        ((ResultGridItem)convertView).toggle(getItem(position).isSelected());
        return convertView;
    }
}
