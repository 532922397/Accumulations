package com.example.liulu.accumulations.wiget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;

import com.example.liulu.accumulations.R;


public class ViewBitmapUtils {

    public static Bitmap getViewBitmap(String text, Context context, int width, int height){
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Bitmap circleBm = ((BitmapDrawable)context.getResources().getDrawable(R.drawable.xiaoma_intro_page4_img1)).getBitmap();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawBitmap(circleBm, null, new RectF(0,0,width,height), paint);
        paint.setTextSize(Utils.dp2Px(24));
        int textWidth = (int) paint.measureText(text);
        Paint.FontMetrics fm = paint.getFontMetrics();
        int needHeight = (int) ((fm.descent - fm.ascent)/2);
        paint.setColor(context.getResources().getColor(R.color.head_text_color));
        canvas.drawText(text, (width - textWidth)/2, (height+needHeight)/2, paint);
        return bitmap;
    }
}
