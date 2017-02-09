package com.example.liulu.accumulations.rxjava;

import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.liulu.accumulations.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.scheduler_fragment, container, false);
        ButterKnife.bind(this,view);
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

    private void doJustOne() {

    }

    private void doJustFour() {

    }

    private void doJustThree() {

    }

    private void doBaseOperation() {

    }


    private void doJustSix() {
    }

    private void doJustFive() {

    }

    private void doOnemapOperation() {

    }

    private void doJustTwo() {

    }

    private void doLongOperation() {

    }

    private void doTwomapWithLongOperation() {

    }

    private void doJust() {
        Observable
                .just(1, 2, 3)
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

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
