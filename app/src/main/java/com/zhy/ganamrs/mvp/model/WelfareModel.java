package com.zhy.ganamrs.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.zhy.ganamrs.app.GreenDaoHelper;
import com.zhy.ganamrs.mvp.contract.WelfareContract;
import com.zhy.ganamrs.mvp.model.api.service.CommonService;
import com.zhy.ganamrs.mvp.model.entity.DaoGankEntity;
import com.zhy.ganamrs.mvp.model.entity.GankEntity;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class WelfareModel extends BaseModel implements WelfareContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public WelfareModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public Observable<GankEntity> getRandomGirl() {
        Observable<GankEntity> randomGirl = mRepositoryManager.obtainRetrofitService(CommonService.class)
                .getRandomGirl();
        return randomGirl;
    }

    @Override
    public void addGankEntity(DaoGankEntity daoGankEntity) {
        GreenDaoHelper.getDaoSession().getDaoGankEntityDao().insert(daoGankEntity);
    }
}