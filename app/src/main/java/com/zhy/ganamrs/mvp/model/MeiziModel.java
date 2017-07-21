package com.zhy.ganamrs.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.zhy.ganamrs.app.GreenDaoHelper;
import com.zhy.ganamrs.app.greendao.DaoGankEntityDao;
import com.zhy.ganamrs.app.utils.CategoryType;
import com.zhy.ganamrs.mvp.contract.MeiziContract;
import com.zhy.ganamrs.mvp.model.entity.DaoGankEntity;

import java.util.List;

import javax.inject.Inject;


@ActivityScope
public class MeiziModel extends BaseModel implements MeiziContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public MeiziModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
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
    public List<DaoGankEntity> getEntity() {
        return GreenDaoHelper.getDaoSession().getDaoGankEntityDao()
                .queryBuilder()
                .where(DaoGankEntityDao.Properties.Type.eq(CategoryType.GIRLS_STR))
                .orderDesc(DaoGankEntityDao.Properties.Addtime)
                .list();
    }
}