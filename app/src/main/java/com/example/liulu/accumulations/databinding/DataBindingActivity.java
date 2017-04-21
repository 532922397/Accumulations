package com.example.liulu.accumulations.databinding;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.model.TestDataBinding;

/**
 * 1、布局文件以layout为根布局
 * 2、data标签定义model类
 * 3、布局控件绑定model类数据
 * 4、自动生成布局文件名+dataBinding类
 * 5、实现绑定
 * 6、实现监听
 */
public class DataBindingActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindingBinding dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        // 绑定方式一
        dataBinding.setUser(new TestDataBinding("刘录", "55kg", 18, '男'));
        // 绑定方式二
//        ActivityDataBindingBinding inflate = ActivityDataBindingBinding.inflate(getLayoutInflater());

    }


}
