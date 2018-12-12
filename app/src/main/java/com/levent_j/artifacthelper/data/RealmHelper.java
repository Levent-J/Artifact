package com.levent_j.artifacthelper.data;

import com.levent_j.artifacthelper.base.BasePresenter;
import com.levent_j.artifacthelper.model.CardModel;
import com.levent_j.artifacthelper.modules.main.MainPresenter;
import com.levent_j.artifacthelper.pojo.Card;
import com.levent_j.artifacthelper.pojo.CardSetInfo;
import com.levent_j.artifacthelper.pojo.CardSetRespone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/05.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class RealmHelper {
    private static RealmHelper sInstance;
    private Realm mRealm;
    private Map<BasePresenter, Realm> mRealms;

    public static RealmHelper getInstance(){
        if (sInstance == null) {
            synchronized (RealmHelper.class){
                if (sInstance == null) {
                    sInstance = new RealmHelper();
                }
            }
        }
        return sInstance;
    }

    private RealmHelper(){
        mRealms = new HashMap<>();
    }

    public void setRealmObject(BasePresenter presenter) {
        mRealms.put(presenter, Realm.getDefaultInstance());
    }

    public RealmHelper getReamObject(BasePresenter presenter){
        mRealm = mRealms.get(presenter);
        return this;
    }

    public void removeRealmObject(BasePresenter presenter) {
        mRealm = mRealms.get(presenter);
        mRealm.removeAllChangeListeners();
        mRealm.close();
    }

    public void insertOrUpdateCardData(Card pojo, CardSetInfo cardSet, Realm.Transaction.OnSuccess onSuccess){
        CardModel cardModel = new CardModel();
        cardModel.transformFromPojo(pojo, cardSet.name.name);
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(cardModel);
            }
        },onSuccess);
    }

    public void insertOrUpdateCardData(List<Card> pojos, CardSetInfo cardSet){
        List<CardModel> cardModels = new ArrayList<>();
        for (Card pojo : pojos) {
            CardModel cardModel = new CardModel();
            cardModel.transformFromPojo(pojo,cardSet.name.name);
            cardModels.add(cardModel);
        }
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (CardModel cardModel : cardModels) {
                    realm.copyToRealmOrUpdate(cardModel);
                }
            }
        });
    }

    public void queryAllCardaData(RealmChangeListener<RealmResults<CardModel>> changeListener){
        RealmResults<CardModel> realmResults = mRealm
                .where(CardModel.class)
                .in("cardType",new String[]{"Hero", "Creep", "Spell", "Item", "Improvement"})
                .sort("cardType",Sort.ASCENDING,"cardName",Sort.ASCENDING)
                .findAllAsync();
        realmResults.addChangeListener(changeListener);
//        return realmResults;
    }

    public void queryCardData(String cardId, RealmChangeListener<RealmResults<CardModel>> changeListener){
        RealmResults<CardModel> realmResults = mRealm.where(CardModel.class)
                .equalTo("cardId",cardId)
                .findAllAsync();
        realmResults.addChangeListener(changeListener);

    }

    public void queryCardData(String[] cardIds, RealmChangeListener<RealmResults<CardModel>> changeListener){

        RealmResults<CardModel> realmResults = mRealm.where(CardModel.class)
                .in("cardId", cardIds)
                .findAllAsync();
        realmResults.addChangeListener(changeListener);

    }
}
