package com.example.liulu.accumulations.rxjava;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liulu on 2017/2/14.
 */
public class ZhiHuManager {

    private static ZhiHuManager instance;

    public ZhiHuApi zhiHuApi;

    public static ZhiHuManager getInstance() {
        if (instance == null) {
            instance = new ZhiHuManager();
        }
        return instance;
    }

    public ZhiHuApi getZhiHuService() {

        if (zhiHuApi == null) {
            if (zhiHuApi == null) {
                zhiHuApi = new Retrofit.Builder()
                        .baseUrl("http://news-at.zhihu.com")
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(ZhiHuApi.class);

            }

        }
        Log.e("", zhiHuApi.toString());
        return zhiHuApi;
    }

}
