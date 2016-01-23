package com.ui.activities;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ui.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * name: DialogActivity
 * desc:
 * date: 2015-06-26
 * author: David
 * Copyright (c) 2015 David Han
 */
public class OptionDialog extends Dialog {
    protected BaseAdapter mAdapter;
    protected ListView mListview;
    protected List<Item> mItems;

    public OptionDialog(Context context) {
        super(context);
        init();
    }

    public OptionDialog(Context context, int theme) {
        super(context, theme);
        init();
    }

    protected OptionDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }
    public static OptionDialog newInstance(Context context,String title,ArrayList<Item> items){
        OptionDialog dialog = new OptionDialog(context);
        dialog.setTitle(title);
        dialog.setItems(items);
        return dialog;
    }

    public void init() {
        setContentView(R.layout.activty_dialog);
        mItems = new ArrayList<>();

        mListview = (ListView) findViewById(R.id.dialog_listview);
        mAdapter = new BaseAdapter(){


            @Override
            public int getCount() {
                return mItems.size();
            }

            @Override
            public Item getItem(int i) {

                return mItems.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(final int i, View view, ViewGroup viewGroup) {
                if(view == null) {
                    view = getLayoutInflater().inflate(R.layout.action_item, viewGroup, false);
                }
                TextView mTitle = (TextView)view.findViewById(R.id.title);
                TextView mValue = (TextView)view.findViewById(R.id.value);
                ImageView mChevron = (ImageView)view.findViewById(R.id.chevron);
                mChevron.setVisibility(View.GONE);
                mTitle.setText(getItem(i).title);
                mValue.setText(getItem(i).value);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getItem(i).onClickListener.onClick(view);
                        dismiss();
                    }
                });
                return view;
            }
        };
        mListview.setAdapter(mAdapter);

    }
    public void addItem(Item item){
        mItems.add(item);
        mAdapter.notifyDataSetChanged();
    }

    public void setItems(List<Item> items) {
        this.mItems = items;
        mAdapter.notifyDataSetChanged();
    }

    public static class Item implements Serializable{
        public String title;
        public String value;
        public View.OnClickListener onClickListener;
        public Item(String title,String value,View.OnClickListener onClick){
            this.title = title;
            this.value = value;
            this.onClickListener = onClick;
        }
    }

}
