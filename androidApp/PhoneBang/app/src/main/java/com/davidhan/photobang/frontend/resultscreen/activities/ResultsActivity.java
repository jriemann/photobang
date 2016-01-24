package com.davidhan.photobang.frontend.resultscreen.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.davidhan.photobang.R;
import com.davidhan.photobang.backend.base.PhotoObject;
import com.davidhan.photobang.backend.io.Const;
import com.davidhan.photobang.frontend.resultscreen.adapters.ResultsGridViewAdapter;
import com.davidhan.photobang.frontend.resultscreen.views.ResultGridItem;

import java.io.File;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.GridViewWithHeaderAndFooter;

/**
 * name: ResultsActivity
 * desc:
 * date: 2016-01-23
 * author: david
 * Copyright (c) 2016 David Han
 **/
public class ResultsActivity extends Activity {
    @Bind(R.id.results_gridview)
    GridViewWithHeaderAndFooter mGridView;
    @Bind(R.id.results_title)
    TextView mTitle;
    @Bind(R.id.results_toolbar_back)
    ImageButton mBackButton;
    @Bind(R.id.results_toolbar_delete)
    ImageButton mDeleteButton;

    LinearLayout mSelectAllButton;
    ResultsGridViewAdapter mAdapter;
    View mGridHeader;
    private ArrayList<File> algoOutFiles;
    private ArrayList<PhotoObject> photoObjects;
    private int selectedAmnt = 0;
    private int totalAmnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);
        readIntent();
        initView();
    }

    private void readIntent() {
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().getSerializable(Const.ALGO_OUTPUT) != null) {
                algoOutFiles = (ArrayList<File>) getIntent().getExtras().getSerializable(Const.ALGO_OUTPUT);
                photoObjects = new ArrayList<>();
                totalAmnt = algoOutFiles.size();
                for (File file : algoOutFiles) {
                    photoObjects.add(new PhotoObject(file));
                }
                //Collections.addAll(algoOutFiles, ((SerializableArrayList) getIntent().getExtras().getSerializable(Const.ALGO_OUTPUT)).toArray());
            }
        }
    }

    private void initView() {
        mBackButton.setOnClickListener(onClick);
        mDeleteButton.setOnClickListener(onClick);
        mAdapter = new ResultsGridViewAdapter(this);
        mGridHeader = getLayoutInflater().inflate(R.layout.results_header, mGridView, false);
        mGridView.addHeaderView(mGridHeader);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(onGridViewItemClick);
        mGridView.setOnItemLongClickListener(onGridViewItemLongClick);
        mSelectAllButton = (LinearLayout) mGridHeader.findViewById(R.id.results_select_all_button);
        mSelectAllButton.setSelected(false);
        mSelectAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectAllButton.setSelected(!mSelectAllButton.isSelected());
                for (PhotoObject obj : photoObjects) {
                   obj.setSelected(mSelectAllButton.isSelected());
                }
                mAdapter.notifyDataSetInvalidated();
                calculateSelected();
            }
        });
        mTitle.setText("0 out of " + totalAmnt + " selected");

    }

    private AdapterView.OnItemClickListener onGridViewItemClick = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ResultGridItem gridItem = ((ResultGridItem) view);
            gridItem.toggle(!getPhotoObjects().get(position).isSelected());
            getPhotoObjects().get(position).setSelected(!getPhotoObjects().get(position).isSelected());
            calculateSelected();
        }
    };

    private void calculateSelected() {
        selectedAmnt = 0;
        for (PhotoObject obj : photoObjects) {
            if (obj.isSelected()) {
                selectedAmnt++;
            }
        }
        Log.i("tttt selected amnt", String.valueOf(selectedAmnt));

        mTitle.setText(selectedAmnt + " out of " + totalAmnt + " selected");
    }

    private AdapterView.OnItemLongClickListener onGridViewItemLongClick = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(ResultsActivity.this, PreviewModal.class);
            intent.putExtra(Const.UUID, (getPhotoObjects().get(position).getUID()));
            intent.putExtra(Const.FILE, (getPhotoObjects().get(position)).getFile());
            startActivity(intent);
            return false;
        }
    };
    private View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mBackButton) {
                onBackPressed();
            } else if (v == mDeleteButton) {

                Intent intent = new Intent(ResultsActivity.this, ConfirmDeleteModal.class);
                intent.putExtra(Const.SELECTED_AMNT, selectedAmnt);
                intent.putExtra(Const.TOTAL_AMNT, totalAmnt);
                startActivity(intent);
            }
        }
    };

    public PhotoObject findPhotoObject(String uid) {
        for (PhotoObject obj : photoObjects) {
            if (obj.getUID().equals(uid)) {
                return obj;
            }
        }
        return null;
    }

    public ArrayList<PhotoObject> getPhotoObjects() {
        return photoObjects;
    }



}
