package com.example.liulu.accumulations.rxjava;

import retrofit2.http.GET;
import rx.Observable;


public interface ZhiHuApi {
    @GET("/api/4/news/latest")
    Observable<ZhihuDaily> getLastDaily();
}
