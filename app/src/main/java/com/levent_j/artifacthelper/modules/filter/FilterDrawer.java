package com.levent_j.artifacthelper.modules.filter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.levent_j.artifacthelper.R;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/16.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class FilterDrawer extends RelativeLayout {
    public FilterDrawer(Context context) {
        super(context);
    }

    public FilterDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_filter_drawer,this);
    }


}
