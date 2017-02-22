package com.example.liulu.accumulations.imp;

import com.example.liulu.accumulations.dagger2.Dagger2Activity;
import com.example.liulu.accumulations.dagger2.TestUserModule;

import dagger.Component;

/**
 * Created by liulu on 2017/2/22
 */
@Component(modules = TestUserModule.class)
public interface TestUserComponent {

    void inject(Dagger2Activity activity);

}
