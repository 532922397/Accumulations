package com.example.liulu.accumulations.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liulu on 2017/3/20
 */

public class SectorProgressWheel extends View {
    private int circleColor;
    private int bgColor;
    private int fgColor;
    private Paint bgPaint;
    private Paint circlePaint;
    private Paint fgPaint;
    private RectF oval;
    private RectF ovalOutside;
    /*  28 */   private int gapPixel = 2;
    private float percent;
    private float startAngle;

    public SectorProgressWheel(Context context, AttributeSet attrs) {

        super(context, attrs);

        this.bgColor = Color.parseColor("#10000000");
        this.fgColor = Color.parseColor("#ffffff");
        this.circleColor = Color.parseColor("#ffffff");
        this.percent = 0.0F;
        this.startAngle = 270.0F;
        // this.gapPixel = IMUtil.dip2px(context, this.gapPixel);
        init(context);
    }

    private void init(Context context) {
        this.bgPaint = new Paint();
        this.bgPaint.setColor(this.bgColor);

        this.fgPaint = new Paint();
        this.fgPaint.setColor(this.fgColor);
        this.fgPaint.setAntiAlias(true);

        this.circlePaint = new Paint();
        this.circlePaint.setColor(this.circleColor);
        this.circlePaint.setAntiAlias(true);
        this.circlePaint.setFilterBitmap(true);
        this.circlePaint.setFlags(1);
        this.circlePaint.setStyle(Paint.Style.STROKE);
        this.circlePaint.setStrokeWidth(this.gapPixel);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        float xpad = getPaddingLeft() + getPaddingRight();
        float ypad = getPaddingBottom() + getPaddingTop();

        float wwd = w - xpad;
        float hhd = h - ypad;

        this.ovalOutside = new RectF(getPaddingLeft(), getPaddingTop(), getPaddingLeft() + wwd, getPaddingTop() + hhd);
        this.oval = new RectF(getPaddingLeft() + this.gapPixel, getPaddingTop() + this.gapPixel, getPaddingLeft() + wwd - this.gapPixel, getPaddingTop() + hhd - this.gapPixel);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));

        canvas.drawArc(this.ovalOutside, 0.0F, 360.0F, true, this.bgPaint);
        canvas.drawArc(this.ovalOutside, 0.0F, 360.0F, false, this.circlePaint);
        canvas.drawArc(this.oval, this.startAngle, this.percent * 3.6F, true, this.fgPaint);
    }

    public int getBgColor() {
        return this.bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
        refreshTheLayout();
    }

    public int getFgColor() {
        return this.fgColor;
    }

    public void setFgColor(int fgColor) {
        this.fgColor = fgColor;
        refreshTheLayout();
    }

    private void refreshTheLayout() {
        invalidate();
        requestLayout();
    }

    public float getStartAngle() {
        return this.startAngle;
    }

    public void setStartAngle(float startAngle) {
        this.startAngle = (startAngle + 270.0F);
        invalidate();
        requestLayout();
    }

    public float getPercent() {
        return this.percent;
    }

    public void setProgress(float percent) {
        this.percent = percent;
        invalidate();
        requestLayout();
    }
}