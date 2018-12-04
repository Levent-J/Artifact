package com.levent_j.artifacthelper.pojo;

import com.google.gson.annotations.SerializedName;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/04.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class CardSetUrlRespone {
    @SerializedName("cdn_root")
    private String mCdnRoot;
    @SerializedName("expire_time")
    private Long mExpireTime;
    @SerializedName("url")
    private String mUrl;

    public String getCdnRoot() {
        return mCdnRoot;
    }

    public void setCdnRoot(String cdnRoot) {
        mCdnRoot = cdnRoot;
    }

    public Long getExpireTime() {
        return mExpireTime;
    }

    public void setExpireTime(Long expireTime) {
        mExpireTime = expireTime;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
