package com.example.liulu.accumulations.rxjava;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.wiget.Log;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by liulu on 2017/2/15
 */
public class PollingFragment extends Fragment {
    private int N = 0;
    private CompositeSubscription compositeSubscription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.polling_fragment, container, false);
        ButterKnife.bind(this, view);
        compositeSubscription = new CompositeSubscription();
        return view;
    }

    @OnClick(R.id.btn_polling)
    public void onClick(View view) {
        Subscription subscribe = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                Schedulers.newThread().createWorker()
                        .schedulePeriodically(new Action0() { // 延迟--周期--单位
                            @Override
                            public void call() {
                                subscriber.onNext("" + (N++));
                            }
                        }, 0, 1000, TimeUnit.MICROSECONDS);
            }
        })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        printLog(s);
                    }
                });
        compositeSubscription.add(subscribe);
    }

    private void printLog(String s) {
        Log.e("liulu", s);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeSubscription.unsubscribe();
    }

}
