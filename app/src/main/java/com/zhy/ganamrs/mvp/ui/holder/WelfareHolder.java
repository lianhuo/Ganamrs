package com.zhy.ganamrs.mvp.ui.holder;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.utils.ArmsUtils;

/**
 * Created by Administrator on 2017/7/6.
 */

public class WelfareHolder extends BaseViewHolder {

    public AppComponent mAppComponent;
    public ImageLoader mImageLoader;//用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架
    public WelfareHolder(View view) {
        super(view);
        mAppComponent = ArmsUtils.obtainAppComponentFromContext(itemView.getContext());
        mImageLoader = mAppComponent.imageLoader();
    }
}
