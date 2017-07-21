package com.zhy.ganamrs.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;
import com.zhy.ganamrs.R;
import com.zhy.ganamrs.app.base.BaseFragment;
import com.zhy.ganamrs.di.component.DaggerMeiziComponent;
import com.zhy.ganamrs.di.module.MeiziModule;
import com.zhy.ganamrs.mvp.contract.MeiziContract;
import com.zhy.ganamrs.mvp.model.entity.DaoGankEntity;
import com.zhy.ganamrs.mvp.presenter.MeiziPresenter;
import com.zhy.ganamrs.mvp.ui.adapter.MeiziAdapter;
import com.zhy.ganamrs.mvp.ui.widget.SpacesItemDecoration;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.jess.arms.utils.Preconditions.checkNotNull;
import static com.zhy.ganamrs.R.id.recyclerView;


public class MeiziFragment extends BaseFragment<MeiziPresenter> implements MeiziContract.View , SwipeRefreshLayout.OnRefreshListener{


    @BindView(recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private MeiziAdapter mAdapter;

    public static MeiziFragment newInstance() {
        MeiziFragment fragment = new MeiziFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerMeiziComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .meiziModule(new MeiziModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_refresh_list, container, false);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        UiUtils.configRecycleView(mRecyclerView,new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new MeiziAdapter(null);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {

        });

        TextView textView = new TextView(getContext());
        textView.setText("没有更多内容了");
        textView.setGravity(Gravity.CENTER);
        mAdapter.setEmptyView(textView);

        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        mRecyclerView.addItemDecoration(decoration);

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onFragmentFirstVisible() {
        //去服务器下载数据
        mPresenter.requestData(true);
    }

    @Override
    public void startLoadMore() {
        Observable.just(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> mSwipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public void endLoadMore() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setAdapter(List<DaoGankEntity> entity) {
        mAdapter.setNewData(entity);
    }

    /**
     * 此方法是让外部调用使fragment做一些操作的,比如说外部的activity想让fragment对象执行一些方法,
     * 建议在有多个需要让外界调用的方法时,统一传Message,通过what字段,来区分不同的方法,在setData
     * 方法中就可以switch做不同的操作,这样就可以用统一的入口方法做不同的事
     * <p>
     * 使用此方法时请注意调用时fragment的生命周期,如果调用此setData方法时onCreate还没执行
     * setData里却调用了presenter的方法时,是会报空的,因为dagger注入是在onCreated方法中执行的,然后才创建的presenter
     * 如果要做一些初始化操作,可以不必让外部调setData,在initData中初始化就可以了
     *
     * @param data
     */

    @Override
    public void setData(Object data) {

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        UiUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {

    }

    @Override
    public void onRefresh() {
        mPresenter.requestData(true);
    }


}
