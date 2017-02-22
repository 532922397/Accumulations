package com.example.liulu.accumulations.model;

/**
 * Created by liulu on 2017/2/22
 */

public class TestDagger2User {

    private String name;
    private int age;
    private String weigth;

    public TestDagger2User(String name, int age, String weigth) {
        this.name = name;
        this.age = age;
        this.weigth = weigth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getWeigth() {
        return weigth;
    }

    public void setWeigth(String weigth) {
        this.weigth = weigth;
    }
}
