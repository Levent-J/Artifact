package com.levent_j.artifacthelper.modules.filter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.levent_j.artifacthelper.R;
import com.levent_j.artifacthelper.base.GlobalData;

import java.util.ArrayList;
import java.util.List;

/*********************************************************************
 * This file is part of Artifact project
 * Created by levent_j on 2018/12/18.
 * Copyright ￼ 2018 NetEase, Inc. - All Rights Reserved
 *********************************************************************/
public class TypeListAdapter extends RecyclerView.Adapter<TypeListAdapter.VH> {

    private List<FilterTypeModel> mData;
    private ITypeItemClickCallback mItemClickCallback;

    public TypeListAdapter(ITypeItemClickCallback mItemClickCallback) {
        this.mItemClickCallback = mItemClickCallback;
        mData = new ArrayList<>();

        initFilterType();
    }

    public List<FilterTypeModel> getmData() {
        return mData;
    }

    private void initFilterType(){
        FilterTypeModel model0 = new FilterTypeModel("扩展包",true);
        FilterTypeModel model1 = new FilterTypeModel("颜色",false);
        FilterTypeModel model2 = new FilterTypeModel("类型",false);
        FilterTypeModel model3 = new FilterTypeModel("费用",false);
        FilterTypeModel model4 = new FilterTypeModel("排序方式",false);

        mData.add(model0);
        mData.add(model1);
        mData.add(model2);
        mData.add(model3);
        mData.add(model4);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_filter_type, viewGroup, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        FilterTypeModel type = mData.get(i);
        vh.bindData(type);

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickCallback.onClickTypeItem(type);

                for (FilterTypeModel model : mData) {
                    model.selected = model==type;
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class VH extends RecyclerView.ViewHolder {

        private TextView mTypeContent;

        public VH(@NonNull View itemView) {
            super(itemView);
            mTypeContent = itemView.findViewById(R.id.tv_filter_type);
        }

        public void bindData(FilterTypeModel type) {
            mTypeContent.setText(type.name);
            mTypeContent.setBackgroundColor(type.selected ?
                    GlobalData.app().getResources().getColor(R.color.color_text_white)
                    : GlobalData.app().getResources().getColor(R.color.color_bbbbbb));
        }
    }

    public interface ITypeItemClickCallback{
        void onClickTypeItem(FilterTypeModel model);
    }
}
