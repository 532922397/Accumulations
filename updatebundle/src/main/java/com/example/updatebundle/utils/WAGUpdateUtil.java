package com.example.updatebundle.utils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.example.updatebundle.business.WAGUpdateService;

import java.io.IOException;
import java.security.interfaces.RSAPublicKey;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liulu on 2017/1/3.
 */

public class WAGUpdateUtil {
    private static Context instance;

    public static void init(Context context) {
        instance = context;
    }

    public static Context getApplicationContext() {
        return instance;
    }

    public static void startUpdate(Context context, String md5, String url) {
        Intent intent = new Intent(context, WAGUpdateService.class);
        intent.putExtra("url", url);
        intent.putExtra("md5", md5);
        context.startService(intent);
    }


    public static void checkUpdate(String url, final RSAPublicKey publicKey) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request build = new Request.Builder().url(url).build();
        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("liulu", "网络请求失败：" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.code() == 200) {
                    String encrypt = response.body().toString();
                    decryptHandle(encrypt, publicKey);
                }
            }
        });
    }

    private static void decryptHandle(String encrypt, RSAPublicKey publicKey) {
        String decrypt = WAGRSAUtils.decrypt(encrypt, publicKey);
        if (TextUtils.isEmpty(decrypt)) {
            return;
        }
        String[] split = decrypt.split(",");
        String md5 = split[0];
        String url = split[1];
        WAGUpdateUtil.startUpdate(getApplicationContext(), md5, url);
    }
}