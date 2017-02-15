package com.example.liulu.accumulations.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.wiget.Log;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jh on 2017/2/15.
 */
public class DebounceEditTextFragment extends Fragment {

    @Bind(R.id.edit_search)
    EditText editSearch;
    private CompositeSubscription compositeSubscription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.debounce_fragment, container, false);
        ButterKnife.bind(this, view);
        compositeSubscription = new CompositeSubscription();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Subscription subscribe = RxTextView.textChangeEvents(editSearch)
                .debounce(400, TimeUnit.MICROSECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TextViewTextChangeEvent>() {
                    @Override
                    public void onCompleted() {
                        printLog("Completed");
                    }


                    @Override
                    public void onError(Throwable e) {
                        printLog("Error");
                    }

                    @Override
                    public void onNext(TextViewTextChangeEvent onTextChangeEvent) {
                        printLog(onTextChangeEvent.text().toString());
                    }
                });
        compositeSubscription.add(subscribe);
    }

    private void printLog(String s) {
        Log.e("liulu", s);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
