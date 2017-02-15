package com.example.liulu.accumulations.rxjava;

import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.wiget.Log;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jh on 2017/2/9.
 */
public class SchedulerFragment extends Fragment {
    String[] data = {"1", "2", "3"};
    @Bind(R.id.btn_just)
    Button btnJust;
    @Bind(R.id.btn_just_one)
    Button btnJustOne;
    @Bind(R.id.btn_just_two)
    Button btnJustTwo;
    @Bind(R.id.btn_just_three)
    Button btnJustThree;
    @Bind(R.id.btn_just_four)
    Button btnJustFour;
    @Bind(R.id.btn_just_five)
    Button btnJustFive;
    @Bind(R.id.btn_just_six)
    Button btnJustSix;
    @Bind(R.id.btn_no_map)
    Button btnNoMap;
    @Bind(R.id.btn_one_map)
    Button btnOneMap;
    @Bind(R.id.btn_long_operation)
    Button btnLongOperation;
    @Bind(R.id.progress_operation_running)
    ProgressBar progressOperationRunning;
    @Bind(R.id.btn_two_map)
    Button btnTwoMap;
    @Bind(R.id.progress_operation_two_running)
    ProgressBar progressOperationTwoRunning;
    private CompositeSubscription compositeSubscription;
    private Subscription baseSubscription;
    private Subscription doMapSubscription;
    private Subscription longSubscription;
    private Subscription twoMapSubscription;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.scheduler_fragment, container, false);
        ButterKnife.bind(this, view);
        compositeSubscription = new CompositeSubscription();
        return view;
    }

    @OnClick({R.id.btn_just, R.id.btn_just_one, R.id.btn_just_two,
            R.id.btn_just_three, R.id.btn_just_four, R.id.btn_just_five,
            R.id.btn_just_six, R.id.btn_no_map, R.id.btn_one_map, R.id.btn_two_map, R.id.btn_long_operation})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_just:
                doJust();
                break;
            case R.id.btn_just_one:
                doJustOne();
                break;
            case R.id.btn_just_two:
                doJustTwo();
                break;
            case R.id.btn_just_three:
                doJustThree();
                break;
            case R.id.btn_just_four:
                doJustFour();
                break;
            case R.id.btn_just_five:
                doJustFive();
                break;
            case R.id.btn_just_six:
                doJustSix();
                break;
            case R.id.btn_no_map:
                doBaseOperation();
                break;
            case R.id.btn_one_map:
                doOnemapOperation();
                break;
            case R.id.btn_long_operation:
                doLongOperation();
                break;
            case R.id.btn_two_map:
                doTwomapWithLongOperation();
                break;
        }
    }

    /**
     * 发送123给观察者并打印
     */
    private void doJust() {
        Observable
                .just(1, 2, 3)
//                .subscribeOn(Schedulers.io()) // 指定线程（io线程）默认为当前线程
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        printLog("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        printLog("onError");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        printLog("onNext" + integer);
                    }
                });
    }

    /**
     * 设置被观察这和观察者的线程
     */
    private void doJustOne() {
        Observable.just(1, 2, 3)
                .subscribeOn(Schedulers.io()) // 指定被观察者的线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定观察者的线程
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        printLog("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        printLog("onError");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        printLog("onNext" + integer);
                    }
                });
    }

    /**
     * 制定观察者的线程，并制定被观察者的执行线程
     */
    private void doJustTwo() {
        Observable.just(1, 2, 3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        printLog("Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        printLog("onError");

                    }

                    @Override
                    public void onNext(Integer integer) {
                        printLog("Next " + integer + " ");
                    }
                });

    }

    /**
     * 使用map对数据发送前更改
     */
    private void doJustThree() {
        Observable.just(1, 2, 3)
                .subscribeOn(Schedulers.io())
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        printLog("map" + integer);
                        return integer + "a";
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        printLog("Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        printLog("onError");

                    }

                    @Override
                    public void onNext(String s) {
                        printLog("Next " + s);

                    }

                });
    }

    /**
     * 指定被观察者后使用map对数据转换
     * 并制定被观察者线程使用map转换完之后再定位
     * 到主线程，然后使用map进行转化
     * <p>
     * 之前改动首先全部全改动，之后改动是改一个发一个
     */
    private void doJustFour() {
        Observable.just(1, 2, 3)
                .subscribeOn(Schedulers.io())
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        printLog("map1" + integer);
                        return integer + "---map1";
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        printLog("map2" + s);
                        return "map2" + s;
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        printLog("Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        printLog("onError");

                    }

                    @Override
                    public void onNext(String s) {
                        printLog("Next " + s);

                    }
                });
    }

    /**
     * 指定被观察者后使用map对数据转换
     * 并制定被观察者线程使用map转换完之后再定位
     * 到主线程，然后使用map进行转化
     * 在切换到主线程，然后使用map进行转化
     * <p>
     * 之前改动首先全部全改动，之后改动是改一个发一个
     */
    private void doJustFive() {
        Observable.just(1, 2, 3)
                .subscribeOn(Schedulers.io())
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        printLog("map1" + integer);
                        return "map1" + integer + "a";
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        printLog("map2" + s);
                        return "map2" + s;
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        printLog("map3" + s);
                        return "map3" + s;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        printLog("Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        printLog("onError");

                    }

                    @Override
                    public void onNext(String s) {
                        printLog("Next " + s);

                    }
                });
    }

    /**
     * 在上个例子基础上，两次使用了subscribeOn制定被观察者线程
     * 结果第一次起作用第二次不起作用
     */
    private void doJustSix() {
        Observable.just(1, 2, 3)
                .subscribeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        printLog("map1 " + integer + "a");
                        return integer + "a";
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        printLog("map2 " + s + "b ");
                        return s + "b";
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        printLog("map3 " + s + "c ");
                        return s + "c";
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        printLog("Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        printLog("onError");

                    }

                    @Override
                    public void onNext(String s) {
                        printLog("Next " + s);

                    }

                });
    }

    /**
     * 最基本的使用，没有线程制定，没有过滤等
     */
    private void doOnemapOperation() {
        baseSubscription = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                printLog("订阅在这里开始");
                subscriber.onStart();
                int len = data.length;
                for (int i = 0; i < len; i++) {
                    printLog("在onSubscribe发送到onNext：" + data[i]);
                    subscriber.onNext(data[i]);
                }
                printLog("完成了一次事件--OnCompleted");
                subscriber.onCompleted();
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                printLog("OnCompleted in Subscriber");
            }

            @Override
            public void onError(Throwable e) {
                printLog("onError in Subscriber");
            }

            @Override
            public void onNext(String s) {
                printLog("onNext " + s + " in Subscriber");

            }
        });
        compositeSubscription.add(baseSubscription);
    }

    /**
     * 对数据做map操作，没有线程调度
     */
    private void doBaseOperation() {
        doMapSubscription = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                printLog("订阅开始了");
                subscriber.onStart();
                int len = data.length;
                for (int i = 0; i < len; i++) {
                    printLog("onSubscribe发送给onNext" + data[i]);
                    subscriber.onNext(data[i]);
                }
                printLog("发送已完成");
                subscriber.onCompleted();
            }
        }).map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                String result = s + "a";
                printLog(result);
                return result;
            }
        })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        printLog("OnCompleted in Subscriber");
                    }

                    @Override
                    public void onError(Throwable e) {
                        printLog("onError in Subscriber");
                    }

                    @Override
                    public void onNext(String s) {
                        printLog("onNext " + s + " in Subscriber");
                    }
                });
        compositeSubscription.add(doMapSubscription);

    }

    /**
     * 模拟阻塞操作，如网络请求并切换线程
     */
    private void doLongOperation() {
        progressOperationRunning.setVisibility(View.VISIBLE);
        longSubscription = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                printLog("订阅在这里开始\t");
                subscriber.onStart();
                int length = data.length;
                for (int i = 0; i < length; i++) {
                    dosomethingBlockThread();
                    printLog("onSubscribe发送给onNext\t" + data[i]);
                    subscriber.onNext(data[i]);
                }
                printLog("本次事件完成");
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        printLog("OnCompleted in Subscriber\t");
                        progressOperationRunning.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        printLog("onError in Subscriber\t");
                        progressOperationRunning.setVisibility(View.INVISIBLE);

                    }

                    @Override
                    public void onNext(String s) {
                        printLog("onNext " + s + " in Subscriber");

                    }
                });
        compositeSubscription.add(longSubscription);


    }


    /**
     * 两次map操作加上线程调度
     */
    private void doTwomapWithLongOperation() {
        progressOperationRunning.setVisibility(View.VISIBLE);
        twoMapSubscription = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                printLog("订阅在这里开始\t");
                subscriber.onStart();
                int length = data.length;
                for (int i = 0; i < length; i++) {
                    dosomethingBlockThread();
                    printLog("onSubscribe发送给onNext\t");
                    subscriber.onNext(data[i]);
                }
                printLog("本次事件完毕\t");
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        printLog("map1\t" + s);
                        return "map1\t" + s;
                    }

                })
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        printLog("map2\t" + s);
                        return "map2" + s;
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        printLog("OnCompleted in Subscriber\t");
                        progressOperationTwoRunning.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        printLog("onError in Subscriber\t");
                        progressOperationTwoRunning.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onNext(String s) {
                        printLog("onNext " + s + " in Subscriber\t");

                    }
                });
        compositeSubscription.add(twoMapSubscription);

    }

    /**
     * 阻塞线程睡两秒
     */
    private void dosomethingBlockThread() {
        printLog("开始阻塞");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void printLog(String s) {
        Log.e("liulu", s + getThreadMessage());

    }

    private String getThreadMessage() {
        if (Looper.getMainLooper() == Looper.myLooper())  // 等于当前线程
            return "主线程";
        else return "非主线程";

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeSubscription.unsubscribe();// 解除订阅
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
