package com.zhy.ganamrs.mvp.presenter;

import android.app.Application;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.zhy.ganamrs.app.utils.RxUtils;
import com.zhy.ganamrs.mvp.contract.DetailContract;
import com.zhy.ganamrs.mvp.model.entity.DaoGankEntity;
import com.zhy.ganamrs.mvp.model.entity.GankEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;


@ActivityScope
public class DetailPresenter extends BasePresenter<DetailContract.Model, DetailContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;
    private DaoGankEntity daoGankEntity;

    @Inject
    public DetailPresenter(DetailContract.Model model, DetailContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void getGirl() {
        mModel.getRandomGirl()
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxUtils.bindToLifecycle(mRootView))//使用Rxlifecycle,使Disposable和Activity一起销毁
                .subscribe(new ErrorHandleSubscriber<GankEntity>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull GankEntity gankEntity) {
                            mRootView.setData(gankEntity.results.get(0).url);
                    }
                });
    }

    public void getQuery(String id){
        mRootView.onFavoriteChange(mModel.queryById(id).size() > 0);
    }

    public void removeByid(GankEntity.ResultsBean entity) {
        DaoGankEntity daoGankEntity = entityToDao(entity);
        mModel.removeByid(daoGankEntity._id);
        mRootView.onFavoriteChange(false);
    }

    public void addToFavorites(GankEntity.ResultsBean entity) {
        DaoGankEntity daoGankEntity = entityToDao(entity);
        mModel.addGankEntity(daoGankEntity);
        mRootView.onFavoriteChange(true);
    }

    private DaoGankEntity entityToDao(GankEntity.ResultsBean entity) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        if (daoGankEntity == null){
            daoGankEntity = new DaoGankEntity();
        }
        daoGankEntity._id = entity._id;
        daoGankEntity.createdAt = entity.createdAt;
        daoGankEntity.desc = entity.desc;
        daoGankEntity.publishedAt = entity.publishedAt;
        daoGankEntity.source = entity.source;
        daoGankEntity.type = entity.type;
        daoGankEntity.url = entity.url;
        daoGankEntity.used = entity.used;
        daoGankEntity.who = entity.who;
        daoGankEntity.addtime =str;
        return daoGankEntity;
    }
}