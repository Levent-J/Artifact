package com.levent_j.artifacthelper.modules.detail;

import android.content.Intent;

import com.levent_j.artifacthelper.R;
import com.levent_j.artifacthelper.base.BaseActivity;
import com.levent_j.artifacthelper.util.MyLog;

import java.util.logging.Logger;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/03.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/

public class CardDetailActivity extends BaseActivity{
    @Override
    protected int setLayoutId() {
        return R.layout.activity_card_detail;
    }

    @Override
    protected void initPresenter() {
        MyLog.d("init presenter");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    public static void openActivity(BaseActivity activity){
        Intent intent = new Intent(activity,CardDetailActivity.class);
        activity.startActivity(intent);
    }
}
