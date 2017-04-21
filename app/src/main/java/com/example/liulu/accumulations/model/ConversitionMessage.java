package com.example.liulu.accumulations.model;

/**
 * Created by liulu on 2017/3/21
 */
public class ConversitionMessage {

    private int type;
    private String content;

    public int getType() {
        return type;
    }

    public ConversitionMessage(int type, String content) {
        this.type = type;
        this.content = content;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
