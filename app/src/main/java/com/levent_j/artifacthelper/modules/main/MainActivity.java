package com.levent_j.artifacthelper.modules.main;

import android.view.View;
import android.widget.Button;

import com.levent_j.artifacthelper.R;
import com.levent_j.artifacthelper.base.BaseActivity;
import com.levent_j.artifacthelper.network.Api;
import com.levent_j.artifacthelper.pojo.CardSetRespone;
import com.levent_j.artifacthelper.pojo.CardSetUrlRespone;
import com.levent_j.artifacthelper.util.MyLog;
import com.levent_j.artifacthelper.util.RxCallback;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/03.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class MainActivity extends BaseActivity {

    private Button mTest;

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        mTest = findViewById(R.id.btn_test);
        mTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Api.getInstance()
                        .getCardSetUrl("01")
                        .compose(MainActivity.this.<CardSetUrlRespone>bindToLifecycle())
                        .subscribe(new RxCallback<CardSetUrlRespone>() {

                    @Override
                    public void onSuccess(CardSetUrlRespone cardSetUrlRespone) {
                        String url = cardSetUrlRespone.getCdnRoot()+ cardSetUrlRespone.getUrl();
                        getCardSet(url);
                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                });

//                CardDetailActivity.openActivity(MainActivity.this);
            }
        });
    }

    private void getCardSet(String url) {
        Api.getInstance()
                .getCardSet(url)
                .compose(this.<CardSetRespone>bindToLifecycle())
                .subscribe(new RxCallback<CardSetRespone>() {
                    @Override
                    public void onSuccess(CardSetRespone cardSetRespone) {
                        MyLog.d(cardSetRespone.cardSet.cardList.get(0));
                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                });
    }

    @Override
    protected void initData() {

    }

}