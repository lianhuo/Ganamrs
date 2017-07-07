package com.zhy.ganamrs.mvp.presenter;

import android.app.Application;

import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.zhy.ganamrs.app.utils.RxUtils;
import com.zhy.ganamrs.mvp.contract.CategoryContract;
import com.zhy.ganamrs.mvp.model.entity.GankEntity;
import com.zhy.ganamrs.mvp.ui.adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;


@ActivityScope
public class CategoryPresenter extends BasePresenter<CategoryContract.Model, CategoryContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;
    private int lastUserId = 1;
    private DefaultAdapter mAdapter;
    private int preEndIndex;
    private List<GankEntity.ResultsBean> mData = new ArrayList<>();

    @Inject
    public CategoryPresenter(CategoryContract.Model model, CategoryContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;

//        mAdapter = new CategoryAdapter(mData);
//        mRootView.setAdapter(mAdapter);//设置Adapter
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void requestData(String type, boolean pullToRefresh) {
        if (mAdapter == null) {
            mAdapter = new CategoryAdapter(mData);
            mRootView.setAdapter(mAdapter);//设置Adapter
        }

        if (pullToRefresh){
            lastUserId = 1;//上拉刷新默认只请求第一页
        }else{
            lastUserId++;
        }
        mModel.gank(type,String.valueOf(lastUserId))
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))
                .doOnSubscribe(disposable -> {
                    if (pullToRefresh)
                        mRootView.showLoading();//显示上拉刷新的进度条
                    else
                        mRootView.startLoadMore();//显示下拉加载更多的进度条
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> {
                    if (pullToRefresh)
                        mRootView.hideLoading();//隐藏上拉刷新的进度条
                    else
                        mRootView.endLoadMore();//隐藏下拉加载更多的进度条
                })
                .compose(RxUtils.bindToLifecycle(mRootView))//使用Rxlifecycle,使Disposable和Activity一起销毁
                .subscribe(new ErrorHandleSubscriber<GankEntity>(mErrorHandler) {
                    @Override
                    public void onNext(@NonNull GankEntity gankEntity) {
                        preEndIndex = mData.size();
                        if (pullToRefresh) mData.clear();
                        mData.addAll(gankEntity.results);
                        if (pullToRefresh)
                            mAdapter.notifyDataSetChanged();
                        else
                            mAdapter.notifyItemRangeInserted(preEndIndex, gankEntity.results.size());
                    }
                });
    }
}