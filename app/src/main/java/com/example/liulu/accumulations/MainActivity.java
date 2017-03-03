package com.example.liulu.accumulations;

import android.view.View;

import com.example.liulu.accumulations.android5.FiveActivity;
import com.example.liulu.accumulations.android7.Android7Activity;
import com.example.liulu.accumulations.animation.AnimationActivity;
import com.example.liulu.accumulations.common.BaseActivity;
import com.example.liulu.accumulations.customview.CustomviewActivity;
import com.example.liulu.accumulations.dagger2.Dagger2Activity;
import com.example.liulu.accumulations.integeration.IntegerationActivity;
import com.example.liulu.accumulations.other.OtherActivity;
import com.example.liulu.accumulations.rxjava.RxjavaActivity;
import com.example.liulu.accumulations.wiget.TestHotFix;
import com.taobao.hotfix.HotFixManager;

import butterknife.OnClick;

/**
 * 阿里HotFix
 * 1--注册账号
 * 2==创建应用
 * 3--配置清单列表
 * 4--配置gradle，application初始化
 * 5==得到新旧apk从而得到patch
 * 6--上传patch
 * 7--发布（灰度或全量）
 */
public class MainActivity extends BaseActivity {
    @Override
    protected void initData() {
        // Toast.makeText(MainActivity.this, "old app", Toast.LENGTH_SHORT).show();
        // Toast.makeText(MainActivity.this, "new app", Toast.LENGTH_SHORT).show();
        // String s = StringUtils.filterNumber("111");
        // Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        // TestHotFix.show(this, "hahahahahha");
        TestHotFix.show(this, "我被添加了混淆");

    }
    //    private void show() {
    //        Toast.makeText(MainActivity.this, "show", Toast.LENGTH_SHORT).show();
    //        HotFixManager.getInstance().queryNewHotPatch(); // 手动调用请求服务器是否有更新并进行下一步操作
    //    }
    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.btn_animation, R.id.btn_five, R.id.btn_customview, R.id.btn_intgeration, R.id.btn_intent, R.id.btn_other, R.id.btn_seven, R.id.btn_rxjava, R.id.btn_dagger2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_animation:
                HotFixManager.getInstance().queryNewHotPatch();
                goToActivity(AnimationActivity.class);
                break;
            case R.id.btn_five:
                goToActivity(FiveActivity.class);
                break;
            case R.id.btn_customview:
                goToActivity(CustomviewActivity.class);
                break;
            case R.id.btn_intgeration:
                goToActivity(IntegerationActivity.class);
                break;
            case R.id.btn_intent:
                goToActivity(IntentActivity.class);
                break;
            case R.id.btn_other:
                goToActivity(OtherActivity.class);
                break;
            case R.id.btn_seven:
                goToActivity(Android7Activity.class);
                break;
            case R.id.btn_rxjava:
                goToActivity(RxjavaActivity.class);
                break;
            case R.id.btn_dagger2:
                goToActivity(Dagger2Activity.class);
                break;
        }
    }
}
