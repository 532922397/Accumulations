package com.example.liulu.accumulations.dagger2;

import com.example.liulu.accumulations.model.TestDagger2User;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liulu on 2017/2/22
 */
@Module
public class TestUserModule {
    @Singleton // 单例
    @Provides // 向里提供参数向外提供依赖
    public TestDagger2User providesUser() {

        return new TestDagger2User("刘录", 18, "55kg");
    }
}
