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
import rx.functions.Func1;
import rx.functions.Func2;
import rx.subscriptions.CompositeSubscription;

import static rx.Observable.just;
import static rx.Observable.timer;


public class Operators1Fragment extends Fragment {


    private CompositeSubscription compositeSubscription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.operators1_fragment, container, false);
        ButterKnife.bind(this, view);
        compositeSubscription = new CompositeSubscription();
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


    @OnClick({R.id.btn_just, R.id.btn_from, R.id.btn_repeat, R.id.btn_repeatwhen, R.id.btn_interval, R.id.btn_timer, R.id.btn_range})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_just:
                doJustOperation();
                break;
            case R.id.btn_from:
                doFromOperation();
                break;
            case R.id.btn_repeat:
                doRepeatOperation();
                break;
            case R.id.btn_repeatwhen:
                doRepeatWhenOperation();
                break;
            case R.id.btn_interval:
                doIntervalOperation();
                break;
            case R.id.btn_timer:
                doTimerOperation();
                break;
            case R.id.btn_range:
                doRangeOperation();
                break;
        }
    }

    /**
     * 从3开始连续产生10个数字
     */

    private void doRangeOperation() {
        Observable
                .range(3, 10)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        printLog("Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        printLog("Error");
                    }

                    @Override
                    public void onNext(Integer i) {
                        printLog("Next" + i);
                    }
                });


    }

    /**
     * 每隔1秒输出一个数字
     */
    private void doTimerOperation() {
        timer(1, 1, TimeUnit.SECONDS)
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        printLog("Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        printLog("Error");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        printLog("Next" + aLong);
                    }
                });

    }

    /**
     * 间隔操作
     */
    private void doIntervalOperation() {
        Subscription intervalub = Observable
                .interval(1, TimeUnit.SECONDS)
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        printLog("Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        printLog("Error");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        printLog("Next" + aLong);
                    }
                });
        compositeSubscription.add(intervalub);
    }

    /**
     * 完成一次之后,再每隔1秒重复1次,共重复3次。
     */

    private void doRepeatWhenOperation() {
        Observable
                .just(1, 2, 3, 4)
                .repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {
                    // 重复三次
                    @Override
                    public Observable<?> call(Observable<? extends Void> observable) {
                        return observable.zipWith(Observable.range(1, 4), new Func2<Void, Integer, Integer>() {
                            @Override
                            public Integer call(Void aVoid, Integer integer) {
                                return integer;
                            }
                        }).flatMap(new Func1<Integer, Observable<?>>() {
                            @Override
                            public Observable<?> call(Integer integer) {
                                printLog("delay repeat" + integer);
                                // 1秒重复一次
                                return timer(1, TimeUnit.SECONDS);
                            }
                        });
                    }
                }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                printLog("Completed");
            }

            @Override
            public void onError(Throwable e) {
                printLog("Error");
            }

            @Override
            public void onNext(Integer value) {
                printLog("Next" + value);
            }
        });
    }

    // 从1开始输出3个数,重复2次
    private void doRepeatOperation() {
        Subscription repeat = Observable
                .range(1, 4)
                .repeat(2)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        printLog("Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        printLog("Error");
                    }

                    @Override
                    public void onNext(Integer i) {
                        printLog("Nest" + i);
                    }
                });
        compositeSubscription.add(repeat);

    }

    /**
     * 接受一个数组,与just的区别就是接受的参数不同
     */
    private void doFromOperation() {
        String[] items = {"1", "2", "3", "4"};
        Observable.from(items)
                .subscribe(
                        new Action1<String>() {
                            @Override
                            public void call(String s) {
                                printLog("Next" + s);
                            }
                        },
                        new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                printLog("Error");
                            }
                        }, new Action0() {
                            @Override
                            public void call() {
                                printLog("Completed");
                            }
                        });
    }

    /**
     * just操作符
     */
    private void doJustOperation() {
        Subscription just = just("1", "2", "3", "4") // 基本类型都可以
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        printLog("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        printLog("onError");
                    }

                    @Override
                    public void onNext(String s) {
                        printLog("onNext " + s);
                    }
                });
        compositeSubscription.add(just);


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




