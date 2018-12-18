package com.levent_j.artifacthelper.modules.filter;

import com.levent_j.artifacthelper.base.BasePresenter;
import com.levent_j.artifacthelper.data.RealmHelper;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/18.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class CardFilterPresenter extends BasePresenter {

    private ICardFilterCallback mCardFilterCallback;

    public CardFilterPresenter(ICardFilterCallback mCardFilterCallback) {
        this.mCardFilterCallback = mCardFilterCallback;
        RealmHelper.getInstance().setRealmObject(this);
    }


    @Override
    public void onDestroy() {
        RealmHelper.getInstance().removeRealmObject(this);
    }
}
