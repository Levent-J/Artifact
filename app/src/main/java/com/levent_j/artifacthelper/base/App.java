package com.levent_j.artifacthelper.base;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/03.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化

        //Logger
        Logger.addLogAdapter(new AndroidLogAdapter());

        Realm.init(this);

        //realm
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().name("testRealm.realm").build();
        Realm.setDefaultConfiguration(realmConfiguration);

        //
        GlobalData.initialize(this);
    }
}
