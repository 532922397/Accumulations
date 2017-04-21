package com.example.liulu.accumulations.other;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.adapter.TestLayoutAdapter;
import com.example.liulu.accumulations.common.BaseActivity;
import com.example.liulu.accumulations.model.ConversitionMessage;

import java.util.ArrayList;

import butterknife.Bind;

public class TestLayoutActivity extends BaseActivity {

    @Bind(R.id.recycleview)
    RecyclerView recycleview;
    private ArrayList<ConversitionMessage> messages;

    @Override
    protected void initData() {
        addMessage();
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        recycleview.setAdapter(new TestLayoutAdapter(messages, this));
    }

    private void addMessage() {
        messages = new ArrayList<>();
        messages.add(new ConversitionMessage(1, "我是会员我是会员我是会员我是会员我是会员我是会员"));
        messages.add(new ConversitionMessage(2, "我是游客我是游客我是游客我是游客我是游客我是游客"));
        messages.add(new ConversitionMessage(1, "我是会员我是会员我是会员我是会员我是会员我是会员"));
        messages.add(new ConversitionMessage(2, "我是游客我是游客我是游客我是游客我是游客我是游客"));
        messages.add(new ConversitionMessage(1, "我是会员我是会员我是会员我是会员我是会员我是会员"));
        messages.add(new ConversitionMessage(2, "我是游客我是游客我是游客我是游客我是游客我是游客"));
        messages.add(new ConversitionMessage(1, "我是会员我是会员我是会员我是会员我是会员我是会员"));
        messages.add(new ConversitionMessage(2, "我是游客我是游客我是游客我是游客我是游客我是游客"));
        messages.add(new ConversitionMessage(1, "我是会员我是会员我是会员我是会员我是会员我是会员"));
        messages.add(new ConversitionMessage(2, "我是游客我是游客我是游客我是游客我是游客我是游客"));
        messages.add(new ConversitionMessage(1, "我是会员我是会员我是会员我是会员我是会员我是会员"));
        messages.add(new ConversitionMessage(2, "我是游客我是游客我是游客我是游客我是游客我是游客"));
    }

    @Override
    public int getLayout() {
        return R.layout.activity_test_layout;
    }

}
