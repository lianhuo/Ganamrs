package com.zhy.ganamrs.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.zhy.ganamrs.mvp.contract.CollectContract;
import com.zhy.ganamrs.mvp.model.CollectModel;


@Module
public class CollectModule {
    private CollectContract.View view;

    /**
     * 构建CollectModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public CollectModule(CollectContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    CollectContract.View provideCollectView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    CollectContract.Model provideCollectModel(CollectModel model) {
        return model;
    }
}