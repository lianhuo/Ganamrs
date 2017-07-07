package com.zhy.ganamrs.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.zhy.ganamrs.di.module.WelfareModule;

import com.zhy.ganamrs.mvp.ui.fragment.WelfareFragment;

@ActivityScope
@Component(modules = WelfareModule.class, dependencies = AppComponent.class)
public interface WelfareComponent {
    void inject(WelfareFragment fragment);
}