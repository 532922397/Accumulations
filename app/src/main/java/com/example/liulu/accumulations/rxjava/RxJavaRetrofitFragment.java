package com.example.liulu.accumulations.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.wiget.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jh on 2017/2/14.
 */
public class RxJavaRetrofitFragment extends Fragment {
    Button mBtnGet;
    private Subscription subscription;

    private CompositeSubscription mCompositeSubscription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.retrofit_fragment, container, false);
        ButterKnife.bind(this, view);
        mCompositeSubscription = new CompositeSubscription();
        return view;
    }

    @OnClick(R.id.btn_get)
    public void onClick() {
        android.util.Log.e("liulu", "进入OnNext");
        subscription = ZhiHuManager.getInstance()
                .getZhiHuService()
                .getLastDaily()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ZhihuDaily>() {
                    @Override
                    public void onCompleted() {
                        android.util.Log.e("liulu", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        android.util.Log.e("liulu", "onError==" + e.getMessage());
                    }

                    @Override
                    public void onNext(ZhihuDaily zhihuDaily) {
                        ZhihuDailyItem item = zhihuDaily.getStories().get(0);
                        printLog("Title:" + item.getTitle());
                        printLog("Data:  " + item.getDate());
                        printLog("Id:    " + item.getId());
                        printLog("Type:  " + item.getType());

                    }
                });
        mCompositeSubscription.add(subscription);

    }

    public void printLog(String s) {
        Log.e("liulu", s);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeSubscription.unsubscribe();
    }

}
