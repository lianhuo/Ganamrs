package com.zhy.ganamrs.mvp.ui.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.UiUtils;
import com.zhy.ganamrs.R;
import com.zhy.ganamrs.app.base.BaseFragment;
import com.zhy.ganamrs.di.component.DaggerWelfareComponent;
import com.zhy.ganamrs.di.module.WelfareModule;
import com.zhy.ganamrs.mvp.contract.WelfareContract;
import com.zhy.ganamrs.mvp.model.entity.GankEntity;
import com.zhy.ganamrs.mvp.presenter.WelfarePresenter;
import com.zhy.ganamrs.mvp.ui.adapter.WelfareAdapter;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static com.jess.arms.utils.Preconditions.checkNotNull;
import static com.zhy.ganamrs.R.id.recyclerView;


public class WelfareFragment extends BaseFragment<WelfarePresenter> implements WelfareContract.View, SwipeRefreshLayout.OnRefreshListener{


    @BindView(recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private WelfareAdapter mAdapter;

    public static WelfareFragment newInstance() {
        WelfareFragment fragment = new WelfareFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerWelfareComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .welfareModule(new WelfareModule(this))
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
        UiUtils.configRecycleView(mRecyclerView, new LinearLayoutManager(getActivity()));
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(mRecyclerView);

        mAdapter = new WelfareAdapter(null);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mAdapter.setOnLoadMoreListener(()->mPresenter.requestData(false), mRecyclerView);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            showMessage("双击收藏图片");
            System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
            mHits[mHits.length - 1] = SystemClock.uptimeMillis();
            if (mHits[0] >= (SystemClock.uptimeMillis() - 500)) {
                collectWelfare(adapter,view,position);
            }
        });
        TextView textView = new TextView(getContext());
        textView.setText("没有更多内容了");
        textView.setGravity(Gravity.CENTER);
        mAdapter.setEmptyView(textView);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void collectWelfare(BaseQuickAdapter adapter, View view, int position) {
        animatePhotoLike(view);
        GankEntity.ResultsBean entity = (GankEntity.ResultsBean) adapter.getItem(position);
        mPresenter.addToFavorites(entity);
    }
    private static final DecelerateInterpolator DECCELERATE_INTERPOLATOR = new DecelerateInterpolator();
    private static final AccelerateInterpolator ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();
    private void animatePhotoLike(View view) {
//        View vBgLike = view.findViewById(R.id.vBgLike);
        View ivLike = view.findViewById(R.id.ivLike);
//        vBgLike.setVisibility(View.VISIBLE);
        ivLike.setVisibility(View.VISIBLE);

//        vBgLike.setScaleY(0.1f);
//        vBgLike.setScaleX(0.1f);
//        vBgLike.setAlpha(1f);
        ivLike.setScaleY(0.1f);
        ivLike.setScaleX(0.1f);

        AnimatorSet animatorSet = new AnimatorSet();

//        ObjectAnimator bgScaleYAnim = ObjectAnimator.ofFloat(vBgLike, "scaleY", 0.1f, 1f);
//        bgScaleYAnim.setDuration(200);
//        bgScaleYAnim.setInterpolator(DECCELERATE_INTERPOLATOR);
//        ObjectAnimator bgScaleXAnim = ObjectAnimator.ofFloat(vBgLike, "scaleX", 0.1f, 1f);
//        bgScaleXAnim.setDuration(200);
//        bgScaleXAnim.setInterpolator(DECCELERATE_INTERPOLATOR);
//        ObjectAnimator bgAlphaAnim = ObjectAnimator.ofFloat(vBgLike, "alpha", 1f, 0f);
//        bgAlphaAnim.setDuration(200);
//        bgAlphaAnim.setStartDelay(150);
//        bgAlphaAnim.setInterpolator(DECCELERATE_INTERPOLATOR);

        ObjectAnimator imgScaleUpYAnim = ObjectAnimator.ofFloat(ivLike, "scaleY", 0.1f, 1f);
        imgScaleUpYAnim.setDuration(300);
        imgScaleUpYAnim.setInterpolator(DECCELERATE_INTERPOLATOR);
        ObjectAnimator imgScaleUpXAnim = ObjectAnimator.ofFloat(ivLike, "scaleX", 0.1f, 1f);
        imgScaleUpXAnim.setDuration(300);
        imgScaleUpXAnim.setInterpolator(DECCELERATE_INTERPOLATOR);

        ObjectAnimator imgScaleDownYAnim = ObjectAnimator.ofFloat(ivLike, "scaleY", 1f, 0f);
        imgScaleDownYAnim.setDuration(300);
        imgScaleDownYAnim.setInterpolator(ACCELERATE_INTERPOLATOR);
        ObjectAnimator imgScaleDownXAnim = ObjectAnimator.ofFloat(ivLike, "scaleX", 1f, 0f);
        imgScaleDownXAnim.setDuration(300);
        imgScaleDownXAnim.setInterpolator(ACCELERATE_INTERPOLATOR);

//        animatorSet.playTogether(bgScaleYAnim, bgScaleXAnim, bgAlphaAnim, imgScaleUpYAnim, imgScaleUpXAnim);
        animatorSet.playTogether(imgScaleUpYAnim, imgScaleUpXAnim);
        animatorSet.play(imgScaleDownYAnim).with(imgScaleDownXAnim).after(imgScaleUpYAnim);

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
//                likeAnimationsMap.remove(holder);
//                resetLikeAnimationState(holder);
//                dispatchChangeFinishedIfAllAnimationsEnded(holder);
            }
        });
        animatorSet.start();
    }

    private long[] mHits = new long[2];
    @Override
    protected void onFragmentFirstVisible() {
        //去服务器下载数据
        mPresenter.requestData(true);
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
        Observable.just(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> mSwipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
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

    @Override
    public void startLoadMore() {
        mAdapter.setEnableLoadMore(true);
    }

    @Override
    public void endLoadMore() {
        mAdapter.loadMoreComplete();
    }

    @Override
    public void setNewData(List<GankEntity.ResultsBean> mData) {
        mAdapter.setNewData(mData);
    }

    @Override
    public void setAddData(List<GankEntity.ResultsBean> results) {
        mAdapter.addData(results);
    }

}
