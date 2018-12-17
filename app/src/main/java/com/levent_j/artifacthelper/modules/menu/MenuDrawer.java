package com.levent_j.artifacthelper.modules.menu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.levent_j.artifacthelper.R;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/16.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class MenuDrawer extends RelativeLayout implements View.OnClickListener {

    private LinearLayout mCardList;
    private LinearLayout mHostSet;
    private LinearLayout mMatchSign;
    private LinearLayout mSetting;
    private LinearLayout mAbout;
    private LinearLayout mParent;

    private LinearLayout mCurrent;

    public MenuDrawer(Context context) {
        super(context);
    }

    public MenuDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_menu_drawer, this);

        mCardList = findViewById(R.id.ll_menu_card_list);
        mHostSet = findViewById(R.id.ll_menu_host_cardset);
        mMatchSign = findViewById(R.id.ll_menu_match_sign);
        mSetting = findViewById(R.id.ll_menu_setting);
        mAbout = findViewById(R.id.ll_menu_about);
        mParent = findViewById(R.id.ll_menu_parent);

        mCardList.setOnClickListener(this);
        mHostSet.setOnClickListener(this);
        mMatchSign.setOnClickListener(this);
        mSetting.setOnClickListener(this);
        mAbout.setOnClickListener(this);
        mParent.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_menu_card_list:
                break;
            case R.id.ll_menu_host_cardset:
                break;
            case R.id.ll_menu_match_sign:
                break;
            case R.id.ll_menu_setting:
                break;
            case R.id.ll_menu_about:
                break;

        }
    }
}
