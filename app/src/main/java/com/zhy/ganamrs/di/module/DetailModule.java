package com.zhy.ganamrs.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.zhy.ganamrs.mvp.contract.DetailContract;
import com.zhy.ganamrs.mvp.model.DetailModel;


@Module
public class DetailModule {
    private DetailContract.View view;

    /**
     * 构建DetailModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public DetailModule(DetailContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    DetailContract.View provideDetailView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    DetailContract.Model provideDetailModel(DetailModel model) {
        return model;
    }
}