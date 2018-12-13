package com.levent_j.artifacthelper.modules.detail;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class CardDetailActivity extends BaseActivity implements ICardDetailCallback, RefCardListAdapter.IRefCardItemClickListener {
    private static final String EXTRA_CARD_ID = "card_id";

    private Toolbar mToolbar;
    private ImageView mCardLargeImg;
    private TextView mCardText;
    private TextView mCardRarity;
    private TextView mCardType;
    private TextView mCardSet;
    private TextView mCardColor;
    private TextView mCardIllustrator;
    private TextView mRefCardTitle;
    private RecyclerView mRefCardListView;

    private CardDetailPresenter mCardDetailPresenter;
    private RefCardListAdapter mRefCardListAdapter;

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
        mRefCardListView = findViewById(R.id.rlv_ref_card_list);
        mRefCardTitle = findViewById(R.id.tv_card_ref_card);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mRefCardListAdapter = new RefCardListAdapter(this);
        mRefCardListView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        mRefCardListView.setAdapter(mRefCardListAdapter);
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

        String text = ShowUtls.getHtmlText(cardModel.cardText);
        if (text.isEmpty()){
            mCardText.setVisibility(View.GONE);
        }else {
            mCardText.setText(text);
        }

        mCardRarity.setText(ShowUtls.getRarityText(cardModel.rarity));
        mCardType.setText(ShowUtls.getCardType(cardModel.cardType));
        mCardIllustrator.setText(cardModel.illustrator);
        mCardSet.setText(cardModel.cardSet);
        mCardColor.setText(ShowUtls.getColor(cardModel.color));
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
        MyLog.d(list.size());
        if (list.size()==0){
            mRefCardTitle.setVisibility(View.GONE);
            mRefCardListView.setVisibility(View.GONE);
        }else {
            mRefCardListAdapter.setUpData(list);
        }
    }

    @Override
    public void onCardClick(CardModel cardModel) {
        CardDetailActivity.openActivity(this,cardModel.cardId);
    }
}
