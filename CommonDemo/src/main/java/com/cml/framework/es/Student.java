package com.cml.framework.es;

/**
 * @Auther: cml
 * @Date: 2018-08-24 09:04
 * @Description:
 */
public class Student {
    private Long id = System.currentTimeMillis();
    private String name = "name";
    private boolean enable = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", enable=" + enable +
                '}';
    }
}
