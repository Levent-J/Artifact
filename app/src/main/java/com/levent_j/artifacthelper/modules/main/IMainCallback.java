package com.levent_j.artifacthelper.modules.main;

import com.levent_j.artifacthelper.model.CardModel;
import com.levent_j.artifacthelper.pojo.CardSetRespone;

import io.realm.RealmResults;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/05.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public interface IMainCallback {
    void onGetLocalCardData(RealmResults<CardModel> list);
    void onGetServerCardData(CardSetRespone.CardSet cardSet);
}
