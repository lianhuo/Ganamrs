package com.zhy.ganamrs.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.zhy.ganamrs.di.module.MeiziModule;

import com.zhy.ganamrs.mvp.ui.fragment.MeiziFragment;

@ActivityScope
@Component(modules = MeiziModule.class, dependencies = AppComponent.class)
public interface MeiziComponent {
    void inject(MeiziFragment fragment);
}