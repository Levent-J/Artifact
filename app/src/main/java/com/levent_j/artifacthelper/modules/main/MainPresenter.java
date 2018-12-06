package com.levent_j.artifacthelper.modules.main;

import com.levent_j.artifacthelper.base.BasePresenter;
import com.levent_j.artifacthelper.data.RealmHelper;
import com.levent_j.artifacthelper.model.CardModel;
import com.levent_j.artifacthelper.network.Api;
import com.levent_j.artifacthelper.pojo.Card;
import com.levent_j.artifacthelper.pojo.CardSetInfo;
import com.levent_j.artifacthelper.pojo.CardSetRespone;
import com.levent_j.artifacthelper.pojo.CardSetUrlRespone;
import com.levent_j.artifacthelper.util.Constans;
import com.levent_j.artifacthelper.util.MyLog;
import com.levent_j.artifacthelper.util.RxCallback;
import com.levent_j.artifacthelper.util.RxUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
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

    public void getCardListFromServer(){
        Api.getInstance()
                .getCardSetUrl(Constans.CARD_SET_ARMS)
                .compose(RxUtil.RxSchedulers())
                .subscribe(new RxCallback<CardSetUrlRespone>() {
                    @Override
                    public void onSuccess(CardSetUrlRespone cardSetUrlRespone) {
                        fff(cardSetUrlRespone);
                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                });

    }

    private void fff(CardSetUrlRespone cardSetUrlRespone) {
        Api.getInstance().getCardSet(cardSetUrlRespone.getCdnRoot()+cardSetUrlRespone.getUrl())
                .subscribe(new RxCallback<CardSetRespone>() {
                    @Override
                    public void onSuccess(CardSetRespone cardSetRespone) {
                        CardSetInfo cardset = cardSetRespone.cardSet.cardSetInfo;
                        List<Card> cardList = cardSetRespone.cardSet.cardList;
                        ddd(cardset,cardList);
                        MyLog.d("card set is " + cardset + " has " + cardList.size() + "cards");
                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                });
    }

    private void ddd(CardSetInfo cardset, List<Card> cardList) {
        for(int i=0;i<10;i++){
            Card card = cardList.get(i);
            RealmHelper.getInstance().getReamObject(this).insertOrUpdateCardData(card, cardset, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    MyLog.d("插入完毕");
                }
            });
        }
    }

    public void getCardListFromDB(){
        RealmHelper.getInstance().getReamObject(this).queryAllCardaData(new RealmChangeListener<RealmResults<CardModel>>() {
            @Override
            public void onChange(RealmResults<CardModel> cardModels) {
                mCallback.onGetCardList(cardModels);

            }
        });
    }

    @Override
    public void onDestory() {

    }


}
