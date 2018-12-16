package com.levent_j.artifacthelper.modules.menu;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.levent_j.artifacthelper.R;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/16.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class MenuDrawer extends RelativeLayout {
    public MenuDrawer(Context context) {
        super(context);
    }

    public MenuDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_menu_drawer,this);
    }


}
