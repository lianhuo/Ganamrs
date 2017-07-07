package com.zhy.ganamrs.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.zhy.ganamrs.di.module.CollectModule;

import com.zhy.ganamrs.mvp.ui.fragment.CollectFragment;

@ActivityScope
@Component(modules = CollectModule.class, dependencies = AppComponent.class)
public interface CollectComponent {
    void inject(CollectFragment fragment);
}