package com.zhy.ganamrs.mvp.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.widget.imageloader.glide.GlideImageConfig;
import com.zhy.ganamrs.R;
import com.zhy.ganamrs.mvp.model.entity.GankEntity;
import com.zhy.ganamrs.mvp.ui.holder.WelfareHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */

public class WelfareAdapter extends BaseQuickAdapter<GankEntity.ResultsBean,WelfareHolder> {


    public WelfareAdapter( @Nullable List<GankEntity.ResultsBean> data) {
        super(R.layout.item_girls, data);
    }

    @Override
    protected void convert(WelfareHolder helper, GankEntity.ResultsBean item) {
        helper.mImageLoader.loadImage(helper.mAppComponent.appManager().getCurrentActivity() == null
                        ? helper.mAppComponent.application() : helper.mAppComponent.appManager().getCurrentActivity(),
                GlideImageConfig
                        .builder()
                        .url(item.url)
                        .imageView(helper.getView(R.id.ivImage))
                        .build());
    }
}
