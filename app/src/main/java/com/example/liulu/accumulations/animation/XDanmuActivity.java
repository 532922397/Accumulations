package com.example.liulu.accumulations.animation;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;
import com.orzangleli.xdanmuku.DanmuContainerView;
import com.orzangleli.xdanmuku.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import butterknife.Bind;
import butterknife.OnClick;

public class XDanmuActivity extends BaseActivity {

    @Bind(R.id.danmuContainerView)
    DanmuContainerView danmuContainerView;
    @Bind(R.id.btn_create)
    Button btnCreate;
    public String SEED[] = {"桃树、杏树、梨树，你不让我", "，都开满了花赶趟儿。红的像火，", "花里带着甜味儿，闭了眼，树上", "满是桃儿、杏儿、梨儿!花下成", "嗡地闹着，大小的蝴蝶"};
    @Bind(R.id.btn_bottom)
    Button btnBottom;
    @Bind(R.id.btn_top)
    Button btnTop;
    @Bind(R.id.btn_full)
    Button btnFull;
    private Random random;
    Handler handler = new Handler(Looper.getMainLooper());

    enum TYPE {
        BOTTOM, FULL, TOP, NULL
    }

    @Override
    protected void initData() {
        random = new Random();
        DanmuAdapter adapter = new DanmuAdapter(XDanmuActivity.this);
        danmuContainerView.setAdapter(adapter);
        // 速度
        danmuContainerView.setSpeed(DanmuContainerView.HIGH_SPEED);
        //点击事件
        danmuContainerView.setOnItemClickListener(new DanmuContainerView.OnItemClickListener() {
            @Override
            public void onItemClick(Model model) {
                DanmuEntity entity = (DanmuEntity) model;
                Toast.makeText(XDanmuActivity.this, "点了====" + entity.getContent(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void createDanmu(final TYPE type) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                long l = System.currentTimeMillis();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                final String format = dateFormat.format(new Date(l));
                DanmuEntity entity = new DanmuEntity();
                entity.setTime(format);
                entity.setContent(SEED[random.nextInt(5)]);
                entity.setBackgroundColor(R.color.head_text_color);
                entity.setType(1);
                if (type == TYPE.TOP) {
                    danmuContainerView.setGravity(DanmuContainerView.GRAVITY_TOP);
                } else if (type == TYPE.BOTTOM) {
                    danmuContainerView.setGravity(DanmuContainerView.GRAVITY_BOTTOM);
                } else if (type == TYPE.FULL) {
                    danmuContainerView.setGravity(DanmuContainerView.GRAVITY_FULL);
                } else {
                    danmuContainerView.setGravity(DanmuContainerView.GRAVITY_FULL);
                }

                danmuContainerView.addDanmu(entity);

            }
        }, 1000);


    }

    @OnClick({R.id.btn_create, R.id.btn_bottom, R.id.btn_full, R.id.btn_top})
    public void onCreate(View view) {
        switch (view.getId()) {
            case R.id.btn_create:
                createDanmu(TYPE.NULL);
                break;
            case R.id.btn_bottom:
                createDanmu(TYPE.BOTTOM);
                break;
            case R.id.btn_full:
                createDanmu(TYPE.FULL);
                break;
            case R.id.btn_top:
                createDanmu(TYPE.TOP);
                break;
        }

    }

    @Override
    public int getLayout() {
        return R.layout.activity_danmu;
    }

}
