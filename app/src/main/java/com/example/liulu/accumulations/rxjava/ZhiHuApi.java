package com.example.liulu.accumulations.rxjava;

import retrofit2.http.GET;
import rx.Observable;


/**
 * Created by jh on 2017/2/14.
 */
public interface ZhiHuApi {
    @GET("api/4/news/latest")
    Observable<ZhihuDaily> getLastDaily();
}
