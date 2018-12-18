package com.levent_j.artifacthelper.modules.filter;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/18.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class FilterTypeModel {
    public String name;
    public boolean selected;

    public FilterTypeModel(String name, boolean selected) {
        this.name = name;
        this.selected = selected;
    }
}
