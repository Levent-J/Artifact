package com.levent_j.artifacthelper.modules.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.levent_j.artifacthelper.R;
import com.levent_j.artifacthelper.base.GlideApp;
import com.levent_j.artifacthelper.model.CardModel;

import java.util.ArrayList;
import java.util.List;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/11.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.VH>{

    private List<CardModel> mData;
    private ICardItemClickListener mItemClickListener;


    public CardListAdapter(ICardItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
        this.mData = new ArrayList<>();
    }

    public void setUpData(List<CardModel> models){
        mData.clear();
        mData.addAll(models);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_card,viewGroup,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        CardModel cardModel = mData.get(i);
        vh.bindData(cardModel);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class VH extends RecyclerView.ViewHolder{

        private ImageView mCardImg;

        public VH(@NonNull View itemView) {
            super(itemView);
            mCardImg = itemView.findViewById(R.id.iv_item_card_img);
        }

        public void bindData(CardModel model) {
            GlideApp.with(itemView.getContext())
                    .load(model.largeImgUrl)
                    .into(mCardImg);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onCardItemClick(model);
                }
            });
        }
    }

    public interface ICardItemClickListener{
        void onCardItemClick(CardModel model);
    }
}
