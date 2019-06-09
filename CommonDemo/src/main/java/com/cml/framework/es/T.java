package com.cml.framework.es;

import java.util.Optional;

public class T {
    public static void main(String[] args) {
        String a = null;
        Integer b = Optional.ofNullable(a).map(t -> Integer.parseInt(t)).orElse(null);
        System.out.println(b);
    }
}
