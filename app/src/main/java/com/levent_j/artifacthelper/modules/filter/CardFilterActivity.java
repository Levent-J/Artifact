package com.levent_j.artifacthelper.modules.filter;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.levent_j.artifacthelper.R;
import com.levent_j.artifacthelper.base.BaseActivity;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/17.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class CardFilterActivity extends BaseActivity implements TypeListAdapter.ITypeItemClickCallback {

    private RecyclerView mTypeListView;

    private TypeListAdapter mTypeListAdapter;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_card_filter;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        mTypeListView = findViewById(R.id.rlv_filter_type);

        mTypeListAdapter = new TypeListAdapter(this);
        mTypeListView.setAdapter(mTypeListAdapter);
        mTypeListView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        FilterTypeModel model = mTypeListAdapter.getmData().get(0);

        onClickTypeItem(model);
    }

    public static void openActivity(BaseActivity activity){
        Intent intent = new Intent(activity,CardFilterActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,R.anim.slide_out_right);
    }

    @Override
    public void onClickTypeItem(FilterTypeModel model) {
        Toast.makeText(this,model.name,Toast.LENGTH_SHORT).show();
    }
}
