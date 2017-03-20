package com.example.liulu.accumulations;

import android.content.Context;
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

import java.lang.reflect.Method;

import butterknife.OnClick;

/**
 * 阿里HotFix
 * 1--注册账号
 * 2--创建应用
 * 3--配置清单列表
 * 4--配置gradle，application初始化
 * 5--得到新旧apk从而得到patch
 * 6--上传patch
 * 7--发布（灰度或全量）
 */

/**
 * 两个参数：
 * 1。packageName  包名，要得到Context的包名
 * 2。flags  标志位，有CONTEXT_INCLUDE_CODE和CONTEXT_IGNORE_SECURITY两个选项。CONTEXT_INCLUDE_CODE的意思是包括代码，也就是说可以执行这个包里面的代码。CONTEXT_IGNORE_SECURITY的意思是忽略安全警告，如果不加这个标志的话，有些功能是用不了的，会出现安全警告。
 */
public class MainActivity extends BaseActivity {
    @Override
    protected void initData() {
        testHotFix();

    }

    private void testHotFix() {
    /*基本测试*/
        // Toast.makeText(MainActivity.this, "old app", Toast.LENGTH_SHORT).show();
        // Toast.makeText(MainActivity.this, "new app", Toast.LENGTH_SHORT).show();

        /*添加工具类方法*/
        // String s = StringUtils.filterNumber("111");
        // Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

        /*添加全局变量*/
        // TestHotFix.show(this, "添加全局变量后" + a);

        /*混淆*/
        // TestProguard.show(this, "我被添加了混淆");

        /*测试拥有double、float类型的参数*/
        // TestHotFix.showCLong(this, 34545);

        /*新增方法不可以定义但是可以通过新增类的方法*/
        // NewClass.show("我是新增类", getApplicationContext());

        /*构造不能被patch*/
        // TestHotFix.show(this, "未修改构造前");
        // new TestHotFixModle(this);

        /*参数超过8*/
        // TestHotFix.showMax(this, "", "", "", "", "", "", "", "");

        /*测试泛型*/
        // TestHotFixModle<String> modle = new TestHotFixModle<String>();
        // modle.setData("我是泛型，你来咬我啊！");
        // TestHotFix.show(this, modle.getData());

        /*尝试资源修复*/
        //  ivTest.setImageResource(R.drawable.ic_add_circle_blue_700_24dp);
        // TestHotFix.show(this, "我被添加了混淆");

        /*测试反射静态方法*/
        try {
            // Class<?> toastClass = Class.forName("com.example.liulu.accumulations.wiget.TestHotFix");
            // TestHotFix testHotFix = (TestHotFix) toastClass.newInstance();
            Method test2 = TestHotFix.class.getDeclaredMethod("test2", String.class, Context.class);
            test2.invoke(TestHotFix.class.newInstance(), "被成功反射静态方法", MainActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*测试非反射静态方法*/
//        try {
//           // Class<?> name = Class.forName("com.example.liulu.accumulations.wiget.TestHotFix");
//            Method method = TestHotFix.class.getDeclaredMethod("test1", String.class, Context.class);
//            // method.setAccessible(true);
//            method.invoke(TestHotFix.class.newInstance(), "被成功反射非静态方法", MainActivity.this);
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.e("liulu", e.getMessage());
//        }
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
