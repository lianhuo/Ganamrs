package com.zhy.ganamrs.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.zhy.ganamrs.di.module.DetailModule;
import com.zhy.ganamrs.mvp.ui.activity.DetailActivity;

import dagger.Component;

@ActivityScope
@Component(modules = DetailModule.class, dependencies = AppComponent.class)
public interface DetailComponent {
    void inject(DetailActivity activity);
}