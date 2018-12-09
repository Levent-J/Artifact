package com.levent_j.artifacthelper.modules.main;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.levent_j.artifacthelper.R;
import com.levent_j.artifacthelper.base.BaseActivity;
import com.levent_j.artifacthelper.base.GlobalData;
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
public class MainActivity extends BaseActivity implements IMainCallback, Toolbar.OnMenuItemClickListener {

    private Toolbar mToolbar;
    private SearchView mSearchView;

    private MainPresenter mMainPresenter;
    private int syncCardSet = 2;

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
        mToolbar = findViewById(R.id.tool_bar);
        mToolbar.inflateMenu(R.menu.menu_main);
        mToolbar.setOnMenuItemClickListener(this);
    }



    @Override
    protected void initData() {
        //先从本地数据库中查询所有的卡牌
        mMainPresenter.getLocalCardData();
        MyLog.d("init Data");
    }

    @Override
    public void onGetLocalCardData(RealmResults<CardModel> list) {
        MyLog.d(" onGetLocalCardData " +  list.size());

        if (list.size()==0){
            //本地数据库为空 需要从服务器获取
            mMainPresenter.getServerCardData(Constans.SET_CARD_SET_BASE);
            mMainPresenter.getServerCardData(Constans.SET_CARD_SET_ARMS);
            return;
        }
        String s = "";
        for (CardModel model : list) {
            s+="\n";
            s += model.toString();
        }
//        mTest.setText(s);

    }

    @Override
    public void onGetServerCardData(CardSetRespone.CardSet cardSet) {
        MyLog.d(" onGetServerCardData " + cardSet.cardList.size());
        //获取到了新的数据 首先更新数据库
        mMainPresenter.updateLocalCardData(cardSet);
        //其次更新UI展示
//        mTest.setText("一共有"+cardSet.cardList.size());
        syncCardSet--;
        if (syncCardSet==0){
            //同步完成 可以开始展示全部数据了
            mMainPresenter.getLocalCardData();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        MenuItem searchItem = menu.findItem(R.id.menu_main_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setQueryHint("输入关键字");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        int item = menuItem.getItemId();
        switch (item){
            case R.id.menu_main_search:

                break;
            case R.id.menu_main_filter:
                Toast.makeText(this,"筛选",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}