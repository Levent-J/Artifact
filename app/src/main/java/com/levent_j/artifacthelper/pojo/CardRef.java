package com.levent_j.artifacthelper.pojo;

import com.google.gson.annotations.SerializedName;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/04.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class CardRef {

    @SerializedName("card_id")
    public String cardId;

    @SerializedName("ref_type")
    public String refType;

    @SerializedName("count")
    public int count;
}
