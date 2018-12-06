package com.levent_j.artifacthelper.model;

import com.google.gson.annotations.SerializedName;
import com.levent_j.artifacthelper.pojo.Card;
import com.levent_j.artifacthelper.pojo.CardRef;

import io.realm.RealmObject;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/05.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class RefCardModel extends RealmObject{
    public String cardId;
    public String refType;
    public int count;


    public RefCardModel transformFromPojo(CardRef refCard) {
        cardId = refCard.cardId;
        refType = refCard.refType;
        count = refCard.count;
        return this;
    }

    @Override
    public String toString() {
        return "RefCardModel{" +
                "cardId='" + cardId + '\'' +
                ", refType='" + refType + '\'' +
                ", count=" + count +
                '}';
    }
}
