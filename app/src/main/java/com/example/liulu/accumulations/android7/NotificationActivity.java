package com.example.liulu.accumulations.android7;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.NotificationCompat;
import android.view.View;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;

import butterknife.OnClick;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * 通知页面
 */

public class NotificationActivity extends BaseActivity {


    @Override
    protected void initData() {

    }

    @Override
    public int getLayout() {
        return R.layout.activity_notification;
    }

    /**
     * 发送一个普通通知
     *
     * @param view
     */
    @OnClick(R.id.btn_send1)
    public void Send1(View view) {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(NotificationActivity.this, Android7Activity.class), 0);
        Notification build = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                Bitmap bitmap = ((BitmapDrawable) getDrawable(R.drawable.liulu)).getBitmap();
                build = new Notification.Builder(this)
                        .setSmallIcon(R.drawable.circle)
                        .setLargeIcon(bitmap)
                        .setContentTitle("我是普通消息")
                        .setContentText("真帅")
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .setPriority(Notification.PRIORITY_HIGH)
                        .build();
            }
        }

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0, build);


    }

    /**
     * 将扩展布局应用于通知
     */
    @OnClick(R.id.btn_send2)
    public void Send2(View view) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Bitmap bitmap = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            bitmap = ((BitmapDrawable) getDrawable(R.drawable.liulu)).getBitmap();
        }
        builder.setSmallIcon(R.drawable.circle)
                .setLargeIcon(bitmap)
                .setTicker("帅!!!")
                .setContentTitle("我是扩展布局消息")
                .setContentText("真帅")
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .build();
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle("我是大标题");
        // .setSummaryText("我是摘要");
        String[] events = new String[]{"1", "2", "3", "4", "5", "6"};
        // 将事件移动到扩展布局
        for (int i = 0; i < events.length; i++) {
            inboxStyle.addLine(events[i]);

        }
        builder.setStyle(inboxStyle);

        NotificationManager systemService = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        systemService.notify(1, builder.build());

    }


}
