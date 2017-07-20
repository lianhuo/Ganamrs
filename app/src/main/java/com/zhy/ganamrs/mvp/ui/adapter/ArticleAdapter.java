package com.zhy.ganamrs.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhy.ganamrs.R;
import com.zhy.ganamrs.app.utils.CategoryType;
import com.zhy.ganamrs.mvp.model.entity.DaoGankEntity;

import java.util.List;

/**
 * Created by zhy on 17-7-20.
 */

public class ArticleAdapter extends BaseQuickAdapter<DaoGankEntity,BaseViewHolder> {

    public ArticleAdapter( @Nullable List<DaoGankEntity> data) {
        super(R.layout.item_collection, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DaoGankEntity item) {
        helper.setText(R.id.tvDesc, item.desc);
        ImageView ivImage = helper.getView(R.id.ivImage);
        if (item.type.equals(CategoryType.ANDROID_STR)){
            ivImage.setImageResource(R.mipmap.icon_android);
        }else  if (item.type.equals(CategoryType.IOS_STR)){
            ivImage.setImageResource(R.mipmap.icon_apple);
        }else  if (item.type.equals(CategoryType.QIAN_STR)){
            ivImage.setImageResource(R.mipmap.html);
        }
    }
}
