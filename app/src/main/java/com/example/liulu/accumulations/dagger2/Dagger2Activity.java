package com.example.liulu.accumulations.dagger2;

import android.widget.TextView;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;
import com.example.liulu.accumulations.imp.DaggerTestUserComponent;
import com.example.liulu.accumulations.imp.TestUserComponent;
import com.example.liulu.accumulations.model.TestDagger2User;

import javax.inject.Inject;

import butterknife.Bind;
import dagger.Lazy;

public class Dagger2Activity extends BaseActivity {
    @Inject
    TestDagger2User user;
    TestUserComponent component;
    Lazy<TestDagger2User> userLazy; // 懒加载，在用时就会得到一个TestDagger2User对象

    @Bind(R.id.tv_show)
    TextView tvShow;

//    public static String toUtf8(String str) {
//                String result = null;
//                try {
//                         result = new String(str.getBytes("UTF-8"), "UTF-8");
//                     } catch (UnsupportedEncodingException e) {
//                        // TODO Auto-generated catch block
//                         e.printStackTrace();
//                     }
//                 return result;
//            }


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
