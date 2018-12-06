package com.levent_j.artifacthelper.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.components.RxActivity;

import java.util.ArrayList;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/03.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public abstract class BaseActivity extends RxActivity {

    private ArrayList<BasePresenter> mPresenters;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        initPresenter();
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mPresenters != null) {
            for (BasePresenter each : mPresenters) {
                each.onDestroy();
            }
            mPresenters.clear();
        }
    }

    protected abstract int setLayoutId();

    protected abstract void initPresenter();

    protected abstract void initView();

    protected abstract void initData();

    protected void addPresenter(BasePresenter presenter){
        if (mPresenters == null) {
            mPresenters = new ArrayList<>();
        }
        mPresenters.add(presenter);
    }


}