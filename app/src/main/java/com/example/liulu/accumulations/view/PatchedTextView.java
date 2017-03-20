package com.example.liulu.accumulations.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by liulu on 2017/3/20
 */

public class PatchedTextView extends TextView {
    public PatchedTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public PatchedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PatchedTextView(Context context) {
        super(context);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } catch (ArrayIndexOutOfBoundsException e) {
            setText(getText().toString());
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } catch (Exception e) {
            setText(getText().toString());
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public void setGravity(int gravity) {
        try {
            super.setGravity(gravity);
        } catch (ArrayIndexOutOfBoundsException e) {
            setText(getText().toString());
            super.setGravity(gravity);
        }
    }

    public void setText(CharSequence text, BufferType type) {
        try {
            super.setText(text, type);
        } catch (ArrayIndexOutOfBoundsException e) {
            setText(text.toString());
        } catch (Exception e) {
            setText(text.toString());
        }
    }

    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
    }
}