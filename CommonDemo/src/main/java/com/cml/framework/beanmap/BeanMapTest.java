package com.cml.framework.beanmap;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class BeanMapTest {
    public static void main(String[] args) {
        TestBean testBean = new TestBean();
        testBean.setName("name");
        testBean.setAge(1);
        testBean.setTime(new Date(System.currentTimeMillis()));
        BeanMap beanMap = new BeanMap(testBean);

        System.out.println(beanMap);

        TestBean bean = new TestBean();
        try {
            BeanUtils.copyProperties(bean, beanMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(bean);
    }

    public static class TestBean {
        private String name;
        private Integer age;
        private Date time;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Date getTime() {
            return time;
        }

        public void setTime(Date time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "TestBean{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", time=" + time +
                    '}';
        }
    }
}
