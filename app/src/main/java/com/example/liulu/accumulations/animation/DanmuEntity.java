package com.example.liulu.accumulations.animation;

import com.orzangleli.xdanmuku.Model;

/**
 * Created by liulu on 2017/4/19
 */
public class DanmuEntity extends Model {

    public String content;
    public int textColor;
    public int backgroundColor;
    public String time;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
