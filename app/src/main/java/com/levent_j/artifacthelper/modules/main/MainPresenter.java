package com.levent_j.artifacthelper.modules.main;

import com.levent_j.artifacthelper.base.BasePresenter;
import com.levent_j.artifacthelper.data.RealmHelper;
import com.levent_j.artifacthelper.model.CardModel;
import com.levent_j.artifacthelper.network.Api;
import com.levent_j.artifacthelper.pojo.CardSetRespone;
import com.levent_j.artifacthelper.util.MyLog;
import com.levent_j.artifacthelper.util.RxCallback;

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

    /**
     * 获取服务器的卡牌
     * @param cardSet 卡包名称
     */
    public void getServerCardData(String cardSet){
        Api.getInstance()
                .getCardSet(cardSet)
                .compose(((MainActivity) mCallback).bindToLifecycle())
                .subscribe(new RxCallback<CardSetRespone>() {
                    @Override
                    public void onSuccess(CardSetRespone cardSetRespone) {
                        mCallback.onGetServerCardData(cardSetRespone.cardSet);
                    }

                    @Override
                    public void onFailure(String msg) {
                        MyLog.e(msg);
                    }
                });
    }

    /**
     * 获取本地 除建筑 路线等类型之外 其他的所有卡牌数据
     */
    public void getLocalCardData(){
        RealmHelper
                .getInstance()
                .getReamObject(this)
                .queryAllCardaData(new RealmChangeListener<RealmResults<CardModel>>() {
                    @Override
                    public void onChange(RealmResults<CardModel> cardModels) {
                        mCallback.onGetLocalCardData(cardModels);
                    }
                });
    }


    /**
     * 更新本地的卡牌数据
     * @param cardSet
     */
    public void updateLocalCardData(CardSetRespone.CardSet cardSet) {
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
