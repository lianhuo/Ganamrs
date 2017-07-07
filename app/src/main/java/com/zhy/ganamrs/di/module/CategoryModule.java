package com.zhy.ganamrs.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.zhy.ganamrs.mvp.contract.CategoryContract;
import com.zhy.ganamrs.mvp.model.CategoryModel;


@Module
public class CategoryModule {
    private CategoryContract.View view;

    /**
     * 构建CategoryModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public CategoryModule(CategoryContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    CategoryContract.View provideCategoryView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    CategoryContract.Model provideCategoryModel(CategoryModel model) {
        return model;
    }
}