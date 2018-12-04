package com.levent_j.artifacthelper.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/04.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class CardSetInfo {
    @SerializedName("set_id")
    public String setId;

    @SerializedName("pack_item_def")
    public String packItemDef;

    @SerializedName("name")
    public CardNameField name;

    @Override
    public String toString() {
        return "CardSetInfo{" +
                "setId='" + setId + '\'' +
                ", packItemDef='" + packItemDef + '\'' +
                ", name=" + name +
                '}';
    }
}
