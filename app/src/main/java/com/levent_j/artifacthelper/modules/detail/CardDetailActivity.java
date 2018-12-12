package com.levent_j.artifacthelper.modules.detail;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.levent_j.artifacthelper.R;
import com.levent_j.artifacthelper.base.BaseActivity;
import com.levent_j.artifacthelper.base.GlideApp;
import com.levent_j.artifacthelper.model.CardModel;
import com.levent_j.artifacthelper.model.RefCardModel;
import com.levent_j.artifacthelper.util.MyLog;
import com.levent_j.artifacthelper.util.SharedUtil;
import com.levent_j.artifacthelper.util.ShowUtls;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import io.realm.RealmList;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/03.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/

public class CardDetailActivity extends BaseActivity implements ICardDetailCallback {
    private static final String EXTRA_CARD_ID = "card_id";

    private Toolbar mToolbar;
    private ImageView mCardLargeImg;
    private TextView mCardText;
    private TextView mCardRarity;
    private TextView mCardType;
    private TextView mCardSet;
    private TextView mCardColor;
    private TextView mCardIllustrator;

    private CardDetailPresenter mCardDetailPresenter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_card_detail;
    }

    @Override
    protected void initPresenter() {
        mCardDetailPresenter = new CardDetailPresenter(this);

        addPresenter(mCardDetailPresenter);
    }

    @Override
    protected void initView() {
        mToolbar = findViewById(R.id.tool_bar);
        mCardLargeImg = findViewById(R.id.iv_card_large_img);
        mCardText = findViewById(R.id.tv_card_text);
        mCardRarity = findViewById(R.id.tv_card_rarity);
        mCardType = findViewById(R.id.tv_card_type);
        mCardSet = findViewById(R.id.tv_card_set);
        mCardColor = findViewById(R.id.tv_card_color);
        mCardIllustrator = findViewById(R.id.tv_card_illustrator);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void initData() {
        String cardId = getIntent().getStringExtra(EXTRA_CARD_ID);
        MyLog.d("card  id " + cardId);
        mCardDetailPresenter.getCardModel(cardId);

    }

    public static void openActivity(BaseActivity activity, String cardId) {
        Intent intent = new Intent(activity, CardDetailActivity.class);
        intent.putExtra(EXTRA_CARD_ID, cardId);
        activity.startActivity(intent);
    }

    @Override
    public void onGetCardDetail(CardModel cardModel) {
        MyLog.d(cardModel);
        //填充ui
        GlideApp.with(this)
                .load(cardModel.largeImgUrl)
                .into(mCardLargeImg);

        mCardText.setText(cardModel.cardText);
        mCardRarity.setText(ShowUtls.getRarityText(cardModel.rarity));
        mCardType.setText(cardModel.cardType);
        mCardIllustrator.setText(cardModel.illustrator);
        mCardSet.setText(cardModel.cardSet);
        mCardColor.setText(cardModel.color);
        //再获取相关卡牌
        RealmList<RefCardModel> reflist = cardModel.refCards;
        String[] refIds = new String[reflist.size()];
        for (int i = 0; i < refIds.length; i++) {
            refIds[i] = reflist.get(i).cardId;
        }
        mCardDetailPresenter.getRefCardModel(refIds);
    }

    @Override
    public void onGetRefCard(List<CardModel> list) {
        Toast.makeText(this,"相关卡牌有" + list.size() + "张", Toast.LENGTH_SHORT).show();
    }
}
