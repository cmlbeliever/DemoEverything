package com.cml.framework.kryo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: cml
 * @Date: 2018-11-30 17:50
 * @Description:
 */
public class User implements Serializable {
    private String name;
    private int age;
    private boolean man;
    private Date time;
    private Test test;
    private transient int height = 1111;

    public Test getTest() {
        return test;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    static enum Test {
        ONE, TWO, THREE
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

    public boolean isMan() {
        return man;
    }

    public void setMan(boolean man) {
        this.man = man;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
