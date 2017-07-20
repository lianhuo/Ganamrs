package com.zhy.ganamrs.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.zhy.ganamrs.di.module.ArticleModule;

import com.zhy.ganamrs.mvp.ui.fragment.ArticleFragment;

@ActivityScope
@Component(modules = ArticleModule.class, dependencies = AppComponent.class)
public interface ArticleComponent {
    void inject(ArticleFragment fragment);
}