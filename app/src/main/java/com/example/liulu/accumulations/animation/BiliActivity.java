package com.example.liulu.accumulations.animation;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.liulu.accumulations.R;
import com.example.liulu.accumulations.common.BaseActivity;

import java.io.InputStream;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.OnClick;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.controller.IDanmakuView;
import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.IllegalDataException;
import master.flame.danmaku.danmaku.loader.android.DanmakuLoaderFactory;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.BaseCacheStuffer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.model.android.SpannedCacheStuffer;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.IDataSource;
import master.flame.danmaku.ui.widget.DanmakuView;

public class BiliActivity extends BaseActivity {

    @Bind(R.id.btn_show)
    Button btnShow;
    @Bind(R.id.btn_hide)
    Button btnHide;
    @Bind(R.id.btn_pause)
    Button btnPause;
    @Bind(R.id.btn_create)
    Button btnCreate;
    @Bind(R.id.btn_img_text)
    Button btnImgText;
    @Bind(R.id.danmukuview)
    DanmakuView danmukuview;
    private DanmakuContext context;
    private BaseDanmakuParser parser;

    private BaseCacheStuffer.Proxy cacheStufferAdapter = new BaseCacheStuffer.Proxy() {
        @Override
        public void prepareDrawing(BaseDanmaku danmaku, boolean fromWorkerThread) {
           /* Model.getInstance().getGolbalThreadPool().execute(new Runnable() {
                @Override
                public void run() {

                }
            });*/
        }

        @Override
        public void releaseResource(BaseDanmaku danmaku) {
            Log.e("liulu", "releaseResource");
        }
    };
    boolean restart;

    @OnClick({R.id.btn_show, R.id.btn_hide, R.id.btn_pause, R.id.btn_create, R.id.btn_img_text})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_show: // 显示
                danmukuview.show();
                break;
            case R.id.btn_hide: // 隐藏
                danmukuview.hide();
                break;
            case R.id.btn_pause: // 暂停
                if (restart) {
                    danmukuview.resume();
                    restart = false;
                } else {
                    danmukuview.pause();
                    restart = true;
                }
                break;
            case R.id.btn_create: // 纯文本
                addDanmu(false);
                break;
            case R.id.btn_img_text: // 图文
                addDanmuTextAndImg(false);
                break;
        }
    }

    private void addDanmuTextAndImg(boolean isLive) {
        BaseDanmaku danmaku = context.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        Drawable drawable = getResources().getDrawable(R.drawable.liulu);
        drawable.setBounds(0, 0, 100, 100);
        SpannableStringBuilder spannable = createSpannable(drawable);
        danmaku.text = spannable;
        danmaku.padding = 5;
        danmaku.priority = 1;  // 一定会显示, 一般用于本机发送的弹幕
        danmaku.isLive = isLive;
        danmaku.setTime(danmukuview.getCurrentTime() + 1200);
        danmaku.textSize = 25;
        danmaku.textColor = Color.RED;
        // danmaku.textShadowColor = 0; // 重要：如果有图文混排，最好不要设置描边(设textShadowColor=0)，否则会进行两次复杂的绘制导致运行效率降低
        danmaku.underlineColor = Color.GREEN;
        danmukuview.addDanmaku(danmaku);
    }

    private SpannableStringBuilder createSpannable(Drawable drawable) {
        String text = "bitmap";
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        ImageSpan span = new ImageSpan(drawable);//ImageSpan.ALIGN_BOTTOM);
        spannableStringBuilder.setSpan(span, 0, text.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.append("图文混排");
        spannableStringBuilder.setSpan(new BackgroundColorSpan(Color.parseColor("#8A2233B1")), 0, spannableStringBuilder.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return spannableStringBuilder;
    }

    private void addDanmu(boolean isLive) {
        BaseDanmaku danmaku = context.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        if (danmaku != null || danmukuview != null) {
            danmaku.text = "发送了一条纯文本" + System.nanoTime();
            danmaku.padding = 6;
            danmaku.priority = 1;
            danmaku.isLive = isLive; // 是否为直播弹幕
            danmaku.setTime(danmukuview.getCurrentTime() + 1000);
            danmaku.textSize = 25;
            danmaku.textColor = Color.RED;
            danmaku.textShadowColor = Color.WHITE;
            // danmaku.underlineColor = Color.GREEN;
            danmaku.borderColor = Color.GREEN;
            danmukuview.addDanmaku(danmaku);

        }
    }

    @Override
    protected void initData() {
        parser = createParser();
        // 最大行数
        HashMap<Integer, Integer> maxLines = new HashMap<>();
        maxLines.put(BaseDanmaku.TYPE_SCROLL_RL, 5);
        // 是否禁止重叠
        HashMap<Integer, Boolean> overlappingEnable = new HashMap<>();
        overlappingEnable.put(BaseDanmaku.TYPE_SCROLL_RL, true);
        overlappingEnable.put(BaseDanmaku.TYPE_FIX_TOP, true);

        context = DanmakuContext.create();
        context.setMaximumLines(maxLines)
                .preventOverlapping(overlappingEnable)
                .setDanmakuMargin(40)
                .setCacheStuffer(new SpannedCacheStuffer(), cacheStufferAdapter); // 设置图文混排
        danmukuview.prepare(parser, context);
        danmukuview.setOnDanmakuClickListener(new IDanmakuView.OnDanmakuClickListener() {
            @Override
            public boolean onDanmakuClick(IDanmakus danmakus) {
                Log.e("liulu", "onDanmakuClick");
                BaseDanmaku last = danmakus.last();
                if (last != null) {
                    return true;
                }
                return false;
            }

            @Override
            public boolean onDanmakuLongClick(IDanmakus danmakus) {
                Log.e("liulu", "onDanmakuLongClick");
                return false;
            }

            @Override
            public boolean onViewClick(IDanmakuView view) {
                Log.e("liulu", "onViewClick");
                return false;
            }
        });

        danmukuview.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                danmukuview.start();
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {

            }

            @Override
            public void drawingFinished() {

            }
        });

    }

    private BaseDanmakuParser createParser() {
        InputStream stream = getResources().openRawResource(R.raw.comments);
        if (stream == null) {
            return new BaseDanmakuParser() {
                @Override
                protected Danmakus parse() {
                    return new Danmakus();
                }
            };
        }
        BiliDanmukuParser parser = new BiliDanmukuParser();
        ILoader loader = DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_BILI);
        try {
            loader.load(stream);
        } catch (IllegalDataException e) {
            e.printStackTrace();
        }
        IDataSource<?> dataSource = loader.getDataSource();
        parser.load(dataSource);
        return parser;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_bili;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(danmukuview!=null) {
            danmukuview.release();
            danmukuview=null;
        }  
    }
}