package com.levent_j.artifacthelper.modules.main;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.levent_j.artifacthelper.R;
import com.levent_j.artifacthelper.base.BaseActivity;
import com.levent_j.artifacthelper.data.RealmHelper;
import com.levent_j.artifacthelper.model.CardModel;
import com.levent_j.artifacthelper.model.TestPerson;
import com.levent_j.artifacthelper.network.Api;
import com.levent_j.artifacthelper.pojo.Card;
import com.levent_j.artifacthelper.pojo.CardSetInfo;
import com.levent_j.artifacthelper.pojo.CardSetRespone;
import com.levent_j.artifacthelper.pojo.CardSetUrlRespone;
import com.levent_j.artifacthelper.util.Constans;
import com.levent_j.artifacthelper.util.MyLog;
import com.levent_j.artifacthelper.util.RxCallback;
import com.levent_j.artifacthelper.util.RxUtil;

import org.reactivestreams.Subscription;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/03.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class MainActivity extends BaseActivity implements IMainCallback {


    private MainPresenter mMainPresenter;
    private Realm realm;
    private TextView mlog;

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {
        mMainPresenter  = new MainPresenter(this);
    }

    @Override
    protected void initView() {
        mlog = findViewById(R.id.tv_test_log);
        findViewById(R.id.btn_test_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mMainPresenter.getCardListFromServer();
            }
        });

        findViewById(R.id.btn_test_query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMainPresenter.getCardListFromDB();
            }
        });

        findViewById(R.id.btn_test_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmQuery<TestPerson> query = realm.where(TestPerson.class);
                query.equalTo("name","111");
                final RealmResults<TestPerson> realmResults = query.findAll();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realmResults.deleteAllFromRealm();
                    }
                });
            }
        });

        findViewById(R.id.btn_test_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final TestPerson person = new TestPerson();
                person.setName("111");
                person.setAge(222);
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealmOrUpdate(person);
                    }
                });

//                RealmQuery<TestPerson> query = realm.where(TestPerson.class);
//                query.equalTo("name","111");
//                final RealmResults<TestPerson> realmResults = query.findAll();
//                realm.executeTransaction(new Realm.Transaction() {
//                    @Override
//                    public void execute(Realm realm) {
//                        realmResults.deleteAllFromRealm();
//                    }
//                });
            }
        });


    }





    @Override
    protected void initData() {
        realm  = Realm.getDefaultInstance();
    }

    @Override
    public void onGetCardList(RealmResults<CardModel> list) {
        for (CardModel model : list) {

            mlog.setText(mlog.getText()+"\n"+model);
        }
    }
}