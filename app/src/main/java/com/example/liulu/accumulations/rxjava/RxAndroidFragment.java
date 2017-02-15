package com.example.liulu.accumulations.rxjava;

import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.wiget.Log;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func0;
import rx.subscriptions.CompositeSubscription;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

/**
 * Created by jh on 2017/2/15.
 */

public class RxAndroidFragment extends Fragment {
    @Bind(R.id.progress_operation_two_running)
    ProgressBar progressOperationTwoRunning;
    private CompositeSubscription compositeSubscription;
    private Looper backgroundThreadLooper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rxandroid_fragmnet, container, false);
        ButterKnife.bind(this, view);
        compositeSubscription = new CompositeSubscription();

        BackgroundThread backgroundThread = new BackgroundThread();//
        backgroundThread.start();
        backgroundThreadLooper = backgroundThread.getLooper();
        return view;
    }

    @OnClick(R.id.btn_rxandroid)
    public void onclick() {
        onSchedulerButtonClicked();
    }

    private void onSchedulerButtonClicked() {
        sampleObservable()
                .subscribeOn(AndroidSchedulers.from(backgroundThreadLooper))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        printLog("Completed");
                        progressOperationTwoRunning.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        printLog("onError");
                        progressOperationTwoRunning.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onNext(String s) {
                        printLog("onNext" + s);
                        progressOperationTwoRunning.setVisibility(View.INVISIBLE);
                    }
                });
    }

    private void printLog(String s) {
        Log.e("liulu", s);
    }

    private Observable<String> sampleObservable() {
        progressOperationTwoRunning.setVisibility(View.VISIBLE);

        return Observable.defer(new Func0<Observable<String>>() { // 延迟操作，延迟一定时间在订阅时间，可起到即使更新数据
            @Override
            public Observable<String> call() {
                try {
                    // Do some long running operation
                    Thread.sleep(java.util.concurrent.TimeUnit.SECONDS.toMillis(5));
                } catch (InterruptedException e) {
                    throw OnErrorThrowable.from(e);
                }
                return Observable.just("1", "2", "3", "4");
            }
        });
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    static class BackgroundThread extends HandlerThread {

        public BackgroundThread() {
            super("BackgroundThread", THREAD_PRIORITY_BACKGROUND);
        }
    }
}
