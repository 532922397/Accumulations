package com.example.liulu.accumulations.rxjava;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.wiget.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jh on 2017/2/14.
 */
public class Operators2Fragment extends Fragment {


    private CompositeSubscription mCompositeSubscription;
    Subscription subscription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.operators2_fragment, container, false);
        ButterKnife.bind(this, view);
        mCompositeSubscription = new CompositeSubscription();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @OnClick({R.id.btn_buffer, R.id.btn_flarmap, R.id.btn_concatmap, R.id.btn_switchmap})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_buffer:
                doBufferOperation();
                break;
            case R.id.btn_flarmap:
                doflatMap();
                break;
            case R.id.btn_concatmap:
                doConcatmap();
                break;
            case R.id.btn_switchmap:
                doSwitchmap();
                break;
        }
    }

    /**
     * 当源Observable发射一个新的数据项时，如果旧数据项订阅还未完成，
     * 就取消旧订阅数据和停止监视那个数据项产生的Observable,
     * 开始监视新的数据项
     * 所以输出了30,15
     */
    private void doSwitchmap() {
        Observable.just(10, 20, 30).switchMap(new Func1<Integer, Observable<Integer>>() {
            @Override
            public Observable<Integer> call(Integer integer) {
                //10的延迟执行时间为200毫秒、20和30的延迟执行时间为180毫秒
                int delay = 200;
                if (integer > 10)
                    delay = 180;

                return Observable.from(new Integer[]{integer, integer / 2}).delay(delay, TimeUnit.MILLISECONDS);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                printLog("Switch Next" + integer);
            }
        });
    }

    //与flatmap大致相同,只是产生的顺序不同;
    private void doConcatmap() {
        Observable.just(1, 2, 3)
                .concatMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer integer) {
                        // 1的延迟时间为200毫秒，2、3的延迟时间为180毫秒
                        int delay = 200;
                        if (integer > 1) {
                            delay = 180;
                        }
                        return Observable.from(new Integer[]{integer, integer / 2}).delay(delay, TimeUnit.MICROSECONDS);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        printLog("Concat Next" + integer);
                    }
                });
    }

    //与map一样,都是对数据转化,map是一对一,flatmap一对多;
    private void doflatMap() {
        Bean bean = new Bean();
        Observable.just(bean)
                .flatMap(new Func1<Bean, Observable<String>>() {
                    @Override
                    public Observable<String> call(Bean bean) {
                        return Observable.from(bean.getData());
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        printLog("Flat Next" + s);
                    }
                });

    }

    //一共发送10次信息,Observable每次缓存3秒一起发送
    private void doBufferOperation() {
        Subscription subscribe = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int i = 0; i < 10; i++) {
                    subscriber.onNext("" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).subscribeOn(Schedulers.io())
                .buffer(3, TimeUnit.SECONDS)
                .subscribe(new Action1<List<String>>() {
                    @Override
                    public void call(List<String> strings) {
                        printLog("收到" + strings.size() + "次");
                        for (int i = 0; i < strings.size(); i++) {
                            printLog(strings.get(i));
                        }
                    }
                });
        mCompositeSubscription.add(subscribe);

    }

    private class Bean {
        String[] data = {"1", "2", "3"};

        public String[] getData() {
            return data;
        }
    }


    private void printLog(String s) {
        Log.e("liulu", s);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeSubscription.unsubscribe();
    }
}
