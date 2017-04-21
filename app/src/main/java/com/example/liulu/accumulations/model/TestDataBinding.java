package com.example.liulu.accumulations.model;

import android.util.Log;
import android.view.View;

/**
 * Created by liulu on 2017/3/22
 */

public class TestDataBinding {
    private boolean ok;
    private String name;
    private String weight;
    private int age;
    private char sex;

    public TestDataBinding(String name, String weight, int age, char sex) {
        this.name = name;
        this.weight = weight;
        this.age = age;
        this.sex = sex;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public void onClick(View view) {
        Log.e("liulu", "点击了！！！！");

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }
}
