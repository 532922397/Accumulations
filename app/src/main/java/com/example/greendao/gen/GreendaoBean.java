package com.example.greendao.gen;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.jetbrains.annotations.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liulu on 2017/6/13
 */
@Entity
public class GreendaoBean {
    @Id(autoincrement = true)
    private Long _id;
    private String name;
    private String age;
    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
//    private String say;
//    private String he;


    public GreendaoBean(Long _id, @NotNull String name, @NotNull String age,
                        String sex, String say, String newS) {
        this._id = _id;
        this.name = name;
        this.age = age;
    }

    public GreendaoBean() {
    }
    public GreendaoBean(Long _id, String name, String age, String sex, String say) {
        this._id = _id;
        this.name = name;
        this.age = age;
    }

    @Generated(hash = 1116425707)
    public GreendaoBean(Long _id, String name, String age, String sex) {
        this._id = _id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }
}
