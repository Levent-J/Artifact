package com.levent_j.artifacthelper.base;

import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;

import com.trello.rxlifecycle2.components.RxActivity;

import java.util.ArrayList;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/03.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public abstract class BaseActivity extends RxActivity {

    private static float sNonDensity;
    private static float sNonScaledDensity;
    private static final int WIDTH_DESIGN = 360;

    private ArrayList<BasePresenter> mPresenters;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        setUpDensity(this,GlobalData.app());
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

    /**
     * 屏幕适配 采取了今日头条的方案
     * @param activity
     * @param context
     */
    private void setUpDensity(BaseActivity activity, final Context context) {
        final DisplayMetrics appDisplayMetrics = context.getResources().getDisplayMetrics();
        if (sNonDensity==0){
            sNonDensity = appDisplayMetrics.density;
            sNonScaledDensity = appDisplayMetrics.scaledDensity;
            context.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig!=null && newConfig.fontScale >0){
                        sNonScaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }
        final float targetDensity = appDisplayMetrics.widthPixels/WIDTH_DESIGN;//以设计图宽度适配
        final float targetScaleDensity = targetDensity * (sNonScaledDensity/sNonDensity);
        final int targetDensityDpi = (int) (160 * targetDensity);

        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.scaledDensity = targetScaleDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;

        final DisplayMetrics activityMetrics = activity.getResources().getDisplayMetrics();
        activityMetrics.density = targetDensity;
        activityMetrics.scaledDensity = targetScaleDensity;
        activityMetrics.densityDpi = targetDensityDpi;
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