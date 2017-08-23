package com.zhy.ganamrs.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.zhy.ganamrs.mvp.contract.CategoryContract;
import com.zhy.ganamrs.mvp.model.api.service.CommonService;
import com.zhy.ganamrs.mvp.model.entity.GankEntity;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class CategoryModel extends BaseModel implements CategoryContract.Model {
    private Gson mGson;
    private Application mApplication;
    public static final int USERS_PER_PAGESIZE = 10;
    @Inject
    public CategoryModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
        super(repositoryManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<GankEntity> gank(String type, String page) {
        return mRepositoryManager.obtainRetrofitService(CommonService.class)
                .gank(type, USERS_PER_PAGESIZE, page);
    }
}