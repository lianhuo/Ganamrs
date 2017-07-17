package com.zhy.ganamrs.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.zhy.ganamrs.mvp.model.entity.DaoGankEntity;
import com.zhy.ganamrs.mvp.model.entity.GankEntity;

import java.util.List;

import io.reactivex.Observable;


public interface DetailContract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

        void setData(String url);
        void onFavoriteChange(boolean isFavorite);
    }

    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model extends IModel {
        Observable<GankEntity> getRandomGirl();
        List<DaoGankEntity> queryById(String  id);
        void removeByid(String id);
        void addGankEntity(DaoGankEntity entity);
    }
}