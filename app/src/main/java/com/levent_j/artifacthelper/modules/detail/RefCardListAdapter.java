package com.levent_j.artifacthelper.modules.detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.levent_j.artifacthelper.R;
import com.levent_j.artifacthelper.base.GlideApp;
import com.levent_j.artifacthelper.model.CardModel;

import java.util.ArrayList;
import java.util.List;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/13.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class RefCardListAdapter extends RecyclerView.Adapter<RefCardListAdapter.VH> {

    private List<CardModel> mDatas;
    private IRefCardItemClickListener mItemClickListener;

    public RefCardListAdapter(IRefCardItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
        mDatas = new ArrayList<>();
    }

    public void setUpData(List<CardModel> models){
        mDatas.clear();
        mDatas.addAll(models);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_ref_card, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        CardModel cardModel = mDatas.get(i);
        vh.bindData(cardModel);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class VH extends RecyclerView.ViewHolder {

        private ImageView mCardImg;

        public VH(@NonNull View itemView) {
            super(itemView);
            mCardImg = itemView.findViewById(R.id.iv_card_large_img);
        }

        public void bindData(CardModel cardModel) {
            GlideApp.with(itemView.getContext())
                    .load(cardModel.largeImgUrl)
                    .into(mCardImg);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onCardClick(cardModel);
                }
            });
        }
    }

    public interface IRefCardItemClickListener {
        void onCardClick(CardModel cardModel);
    }
}
