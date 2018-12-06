package com.levent_j.artifacthelper.modules.main;

import android.widget.TextView;

import com.levent_j.artifacthelper.R;
import com.levent_j.artifacthelper.base.BaseActivity;
import com.levent_j.artifacthelper.model.CardModel;
import com.levent_j.artifacthelper.pojo.CardSetRespone;
import com.levent_j.artifacthelper.util.Constans;
import com.levent_j.artifacthelper.util.MyLog;

import io.realm.RealmResults;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/03.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class MainActivity extends BaseActivity implements IMainCallback {

    private TextView mTest;

    private MainPresenter mMainPresenter;

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
        mTest = findViewById(R.id.tv_test);
    }



    @Override
    protected void initData() {
        //先从本地数据库中查询所有的卡牌
        mMainPresenter.getAllCardListLocal();
        MyLog.d("init Data");
    }

    @Override
    public void onGetAllCardDataLocal(RealmResults<CardModel> list) {
        MyLog.d("get data: " +  list.size());

        if (list.size()==0){
            //本地数据库为空 需要从服务器获取
            mMainPresenter.getCardListFromServer(Constans.SET_CARD_SET_BASE);
            mMainPresenter.getCardListFromServer(Constans.SET_CARD_SET_ARMS);
        }
        String s = "";
        for (CardModel model : list) {
            s+="\n";
            s += model.toString();
        }
        mTest.setText(s);

    }

    @Override
    public void onGetAllCardDataServe(CardSetRespone.CardSet cardSet) {
        //获取到了新的数据 首先更新数据库
        mMainPresenter.updateAllCardData(cardSet);
        //其次更新UI展示
        mTest.setText("一共有"+cardSet.cardList.size());
    }
}