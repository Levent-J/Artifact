package com.levent_j.artifacthelper.modules.filter;

import android.content.Intent;

import com.levent_j.artifacthelper.R;
import com.levent_j.artifacthelper.base.BaseActivity;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/17.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class CardFilterActivity extends BaseActivity {
    @Override
    protected int setLayoutId() {
        return R.layout.activity_card_filter;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

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
}
