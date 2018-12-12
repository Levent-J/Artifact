package com.levent_j.artifacthelper.modules.detail;

import com.levent_j.artifacthelper.model.CardModel;

import java.util.List;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/12.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public interface ICardDetailCallback {
    void onGetCardDetail(CardModel cardModel);
    void onGetRefCard(List<CardModel> list);
}
