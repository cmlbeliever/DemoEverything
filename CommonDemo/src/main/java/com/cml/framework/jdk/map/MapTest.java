package com.cml.framework.jdk.map;

import java.util.HashMap;
import java.util.Objects;

public class MapTest {
    public static void main(String[] args) {

        System.out.println("我的的的身份和jd接dddfdsfsdfsdfsSDF,很快shdfhjd");
        HashMap<App, String> hash = new HashMap<App, String>();
        Long start = System.currentTimeMillis();
        for (Integer i = 0; i < 65530; i++) {
            App app = new App();
            app.setId(100);
            hash.put(app, i.toString());
        }
        App app2 = new App();
        app2.setId(100);
        System.out.println(hash.get(app2));
        Long end = System.currentTimeMillis();
        System.out.println("第一种方式" + (end - start) / 1000);//33
        System.out.println(hash.size());


        HashMap<App, String> hash2 = new HashMap<App, String>();
        Long start2 = System.currentTimeMillis();
        for (Integer i = 0; i < 65530; i++) {
            App app = new App();
            app.setId(i);
            hash2.put(app, i.toString());
        }
        App app3 = new App();
        app3.setId(100);
        hash2.get(app3);
        Long end2 = System.currentTimeMillis();
        System.out.println("第2种方式" + (end2 - start2) / 1000);
    }

    public static class App {

        private Integer id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object obj) {
            // TODO Auto-generated method stub
            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            // TODO Auto-generated method stub
            return id;
        }
    }

    private static class User {
        private String username;
        private String age;

        public User(String username, String age) {
            this.username = username;
            this.age = age;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return Objects.equals(username, user.username);
//            return  false;
        }

        @Override
        public int hashCode() {
            return 1111;
        }

        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", age='" + age + '\'' +
                    '}';
        }
    }
}
