package com.example.liulu.accumulations.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.liulu.accumulations.R;

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
        ZhiHuManager.getInstance()
                .getZhiHuService()
                .getLastDaily()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ZhihuDaily>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ZhihuDaily zhihuDaily) {
                        ZhihuDailyItem zhihuDailyItem = zhihuDaily.getStories().get(0);
                        String title = zhihuDailyItem.getTitle();
                        String date = zhihuDailyItem.getDate();
                        String id = zhihuDailyItem.getId();
                        int type = zhihuDailyItem.getType();
                        printLog("title==" + title + "\n");
                        printLog("date==" + date + "\n");
                        printLog("id==" + id + "\n");
                        printLog("type==" + type + "\n");

                    }
                });

    }

    public void printLog(String log) {
        Log.e("liulu", log);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeSubscription.unsubscribe();
    }

}
