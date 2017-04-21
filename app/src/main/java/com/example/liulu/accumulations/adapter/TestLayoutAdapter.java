package com.example.liulu.accumulations.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.model.ConversitionMessage;
import com.example.liulu.accumulations.view.CustomerComeView;

import java.util.List;

/**
 * Created by liulu on 2017/3/20
 */
public class TestLayoutAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<ConversitionMessage> list;
    private final LayoutInflater inflater;

//    public enum ITEM_TYPE {
//        MEMBER("0"), // 会员
//        OTHER("1");  // 其他
//
//        private String value;
//
//        public String getValue() {
//            return value;
//        }
//        ITEM_TYPE(String value) {
//            this.value = value;
//        }
//    }

    public TestLayoutAdapter(List<ConversitionMessage> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.test_item, null);
        if (viewType == 1) // 会员
            return new ClickableViewHolder(view);
        else               // 其他
            return new OtherViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ClickableViewHolder) {
            ((ClickableViewHolder) holder).customerComeView.setContentText(list.get(position).getContent());
            ((ClickableViewHolder) holder).customerComeView.setContentTextColor(Color.parseColor("#353535"));
            ((ClickableViewHolder) holder).customerComeView.setCustomTalkViewBackground(R.drawable.visit_item_bg_selector);
            ((ClickableViewHolder) holder).customerComeView.setVisibilityBottom(true);
        } else if (holder instanceof OtherViewHolder) {
            ((OtherViewHolder) holder).customerComeView.setContentText(list.get(position).getContent());
            ((OtherViewHolder) holder).customerComeView.setContentTextColor(Color.parseColor("#C5C5C5"));
            ((OtherViewHolder) holder).customerComeView.setCustomTalkViewBackground(R.drawable.visit_normal_bg);
            ((OtherViewHolder) holder).customerComeView.setVisibilityBottom(false);
        }
    }

    @Override
    public int getItemViewType(int position) {
        int type = list.get(position).getType();
        if (type == 1)
            return 1;
        if (type == 2)
            return 2;
        else
            return 0;

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    /*添加item*/
    public void addItem() {
        notifyDataSetChanged();
    }

    // 类型一：可点击
    public static class ClickableViewHolder extends RecyclerView.ViewHolder {
        private CustomerComeView customerComeView;

        public ClickableViewHolder(View itemView) {
            super(itemView);
            customerComeView = (CustomerComeView) itemView;

        }
    }

    // 类型二：不可点击
    public static class OtherViewHolder extends RecyclerView.ViewHolder {
        private CustomerComeView customerComeView;

        public OtherViewHolder(View itemView) {
            super(itemView);
            customerComeView = (CustomerComeView) itemView;
        }
    }
}
