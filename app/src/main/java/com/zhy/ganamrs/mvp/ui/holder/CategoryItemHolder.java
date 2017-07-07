package com.zhy.ganamrs.mvp.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.base.BaseHolder;
import com.zhy.ganamrs.R;
import com.zhy.ganamrs.app.utils.CategoryType;
import com.zhy.ganamrs.mvp.model.entity.GankEntity;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/7/5.
 */

public class CategoryItemHolder extends BaseHolder<GankEntity.ResultsBean> {

    @BindView(R.id.ivImage)
    ImageView ivImage;
    @BindView(R.id.tvDesc)
    TextView tvDesc;
    @BindView(R.id.tvAuthor)
    TextView tvAuthor;
    @BindView(R.id.tvDate)
    TextView tvDate;


    public CategoryItemHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(GankEntity.ResultsBean data, int position) {
        tvDate.setText(data.publishedAt);
        tvAuthor.setText(data.who);
        tvDesc.setText(data.desc);
        if (data.type.equals(CategoryType.ANDROID_STR)){
            ivImage.setImageResource(R.mipmap.icon_android);
        }else  if (data.type.equals(CategoryType.IOS_STR)){
            ivImage.setImageResource(R.mipmap.icon_apple);
        }else  if (data.type.equals(CategoryType.QIAN_STR)){
            ivImage.setImageResource(R.mipmap.html);
        }
    }

}
