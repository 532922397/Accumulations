package com.example.liulu.accumulations.common;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.taobao.hotfix.HotFixManager;
import com.taobao.hotfix.PatchLoadStatusListener;
import com.taobao.hotfix.util.PatchStatusCode;

/**
 * Created by liulu on 2017/1/5
 */

public class DomeApplication extends Application {

    public static Context instance;

    public static Context getContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
    //    initHotFix();
    }

    private void initHotFix() {
        super.onCreate();
        HotFixManager.getInstance().setContext(this)
                .setAppVersion("1.0.2") // 版本号
                .setAppId("96828-1") // 应用申请时的appid
                .setAesKey(null) // 用户自定义aes秘钥, 此时平台无感知这个秘钥, 所以不用担心百川平台会利用你们的补丁做一些非法的事情. 这个参数值必须配合补丁工具的-y参数一起使用, 具体使用参见«Part2 生成patch补丁»的说明, 两者的值需要保持一致, 补丁才能正确被解密进而加载. 可选
                .setSupportHotpatch(true)
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() { // 设置patch加载状态监听器, 该方法参数需要实现
                    @Override
                    public void onload(final int mode, final int code, final String info, final int handlePatchVersion) {
                        // 补丁加载回调通知
                        if (code == PatchStatusCode.CODE_SUCCESS_LOAD) {
                            // TODO: 10/24/16 表明补丁加载成功
                            Log.e("liulu", "补丁加载成功" + "code==" + code + "info==" + info + "handlePatchVersion==" + handlePatchVersion);
                        } else if (code == PatchStatusCode.CODE_ERROR_NEEDRESTART) {
                            // TODO: 10/24/16 表明新补丁生效需要重启. 业务方可自行实现逻辑, 提示用户或者强制重启, 建议: 用户可以监听进入后台事件, 然后应用自杀
                            Log.e("liulu", "补丁加载成功，但许重启" + "code==" + code + "info==" + info + "handlePatchVersion==" + handlePatchVersion);
                        } else if (code == PatchStatusCode.CODE_ERROR_INNERENGINEFAIL) {
                            // 内部引擎加载异常, 推荐此时清空本地补丁, 但是不清空本地版本号, 防止失败补丁重复加载
                            //HotFixManager.getInstance().cleanPatches(false);
                            Log.e("liulu", "内部引擎加载异常" + "code==" + code + "info==" + info + "handlePatchVersion==" + handlePatchVersion);
                        } else {
                            // TODO: 10/25/16 其它错误信息, 查看PatchStatusCode类说明
                            Log.e("liulu", "其他异常" + "code==" + code + "info==" + info + "handlePatchVersion==" + handlePatchVersion);
                        }
                    }
                }).initialize();
    }

}
