package com.example.liulu.accumulations.animation;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.model.LottieComposition;
import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ByNetActivity extends BaseActivity {
    private String url = "http://www.chenailing.cn/EmptyState.json";
    @Bind(R.id.animation_view_network)
    LottieAnimationView animationViewNetwork;


    @Override
    protected void initData() {
        Request build = new Request.Builder()
                .url(url)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        JSONObject json = new JSONObject(response.body().string());
                        LottieComposition.fromJson(getResources(), json, new LottieComposition.OnCompositionLoadedListener() {
                            @Override
                            public void onCompositionLoaded(LottieComposition composition) {
                                setComposition(composition);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void setComposition(LottieComposition composition) {
        animationViewNetwork.setProgress(0);
        animationViewNetwork.loop(true);
        animationViewNetwork.setComposition(composition);
        animationViewNetwork.playAnimation();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_by_net;
    }

    @Override
    protected void onStop() {
        super.onStop();
        animationViewNetwork.cancelAnimation();
    }
}
