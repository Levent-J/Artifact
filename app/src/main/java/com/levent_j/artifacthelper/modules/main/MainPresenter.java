package com.levent_j.artifacthelper.modules.main;

import com.levent_j.artifacthelper.base.BasePresenter;
import com.levent_j.artifacthelper.data.RealmHelper;
import com.levent_j.artifacthelper.model.CardModel;
import com.levent_j.artifacthelper.network.Api;
import com.levent_j.artifacthelper.pojo.Card;
import com.levent_j.artifacthelper.pojo.CardSetRespone;
import com.levent_j.artifacthelper.util.MyLog;
import com.levent_j.artifacthelper.util.RxCallback;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/05.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class MainPresenter extends BasePresenter{

    private IMainCallback mCallback;
    private Realm mRealm;

    public MainPresenter(IMainCallback mCallback) {
        this.mCallback = mCallback;
        RealmHelper.getInstance().setRealmObject(this);
    }

    public void getCardListFromServer(String cardSet){
        Api.getInstance()
                .getCardSet(cardSet)
                .compose(((MainActivity) mCallback).bindToLifecycle())
                .subscribe(new RxCallback<CardSetRespone>() {
                    @Override
                    public void onSuccess(CardSetRespone cardSetRespone) {
                        mCallback.onGetAllCardDataServe(cardSetRespone.cardSet);
                    }

                    @Override
                    public void onFailure(String msg) {
                        MyLog.e(msg);
                    }
                });
    }



    public void getAllCardListLocal(){
        RealmHelper
                .getInstance()
                .getReamObject(this)
                .queryAllCardaData(new RealmChangeListener<RealmResults<CardModel>>() {
                    @Override
                    public void onChange(RealmResults<CardModel> cardModels) {
                        mCallback.onGetAllCardDataLocal(cardModels);
                    }
                });
    }


    public void updateAllCardData(CardSetRespone.CardSet cardSet) {
            RealmHelper.getInstance()
                    .getReamObject(this)
                    .insertOrUpdateCardData(cardSet.cardList, cardSet.cardSetInfo);

    }

    @Override
    public void onDestroy() {
        RealmHelper
                .getInstance()
                .removeRealmObject(this);
    }


}
