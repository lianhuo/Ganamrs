package com.zhy.ganamrs.mvp.model.api.service;

import com.zhy.ganamrs.mvp.model.entity.GankEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 存放通用的一些API
 * Created by jess on 8/5/16 12:05
 * contact with jess.yan.effort@gmail.com
 */
public interface CommonService {

    @GET("api/data/{type}/{pageSize}/{page}")
    Observable<GankEntity> gank(@Path("type") String type, @Path("pageSize") int pageSize, @Path("page") String page);

    // 随机获取一个妹子
    @GET("api/random/data/福利/1")
    Observable<GankEntity> getRandomGirl();
}
