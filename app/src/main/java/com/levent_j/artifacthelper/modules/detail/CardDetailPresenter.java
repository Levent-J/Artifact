package com.levent_j.artifacthelper.modules.detail;

import com.levent_j.artifacthelper.base.BasePresenter;
import com.levent_j.artifacthelper.data.RealmHelper;
import com.levent_j.artifacthelper.model.CardModel;

import java.util.List;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/12.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class CardDetailPresenter extends BasePresenter {

    private ICardDetailCallback mCallback;

    public CardDetailPresenter(ICardDetailCallback mCallback) {
        this.mCallback = mCallback;
        RealmHelper.getInstance().setRealmObject(this);
    }

    public void getCardModel(String cardId){
        RealmHelper.getInstance()
                .getReamObject(this)
                .queryCardData(cardId, new RealmChangeListener<RealmResults<CardModel>>() {
                    @Override
                    public void onChange(RealmResults<CardModel> cardModels) {
                        mCallback.onGetCardDetail(cardModels.first());
                    }
                });
    }

    public void getRefCardModel(String[] cardIds){
        RealmHelper.getInstance()
                .getReamObject(this)
                .queryCardData(cardIds, new RealmChangeListener<RealmResults<CardModel>>() {
                    @Override
                    public void onChange(RealmResults<CardModel> cardModels) {
                        mCallback.onGetRefCard(cardModels);
                    }
                });
    }

    @Override
    public void onDestroy() {

    }
}
