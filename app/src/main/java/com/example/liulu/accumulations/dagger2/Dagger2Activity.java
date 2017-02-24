package com.example.liulu.accumulations.dagger2;

import android.widget.TextView;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;
import com.example.liulu.accumulations.imp.DaggerTestUserComponent;
import com.example.liulu.accumulations.imp.TestUserComponent;
import com.example.liulu.accumulations.model.TestDagger2User;

import javax.inject.Inject;

import butterknife.Bind;

public class Dagger2Activity extends BaseActivity {
    @Inject
    TestDagger2User user;
    TestUserComponent component;
    // Lazy<TestDagger2User> userLazy; // 懒加载，在用时就会得到一个TestDagger2User对象

    @Bind(R.id.tv_show)
    TextView tvShow;


    @Override
    protected void initData() {
        component = DaggerTestUserComponent.builder().build();
        component.inject(this);
        tvShow.setText(user.getName() + ":" + user.getAge() + ":" + user.getWeigth());
    }

    @Override
    public int getLayout() {
        return R.layout.activity_dagger2;
    }

}
