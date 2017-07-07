package com.zhy.ganamrs.mvp.ui.adapter;

import android.view.View;

import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;
import com.zhy.ganamrs.R;
import com.zhy.ganamrs.mvp.model.entity.GankEntity;
import com.zhy.ganamrs.mvp.ui.holder.CategoryItemHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/7/4.
 */

public class CategoryAdapter extends DefaultAdapter<GankEntity.ResultsBean> {

    public CategoryAdapter(List<GankEntity.ResultsBean> infos) {
        super(infos);
    }

    @Override
    public BaseHolder<GankEntity.ResultsBean> getHolder(View v, int viewType) {
        return new CategoryItemHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_android;
    }

}
