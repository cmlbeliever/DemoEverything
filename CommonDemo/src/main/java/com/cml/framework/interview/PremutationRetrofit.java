package com.cml.framework.interview;

import java.util.HashSet;
import java.util.Set;

public class PremutationRetrofit {
    static Set<String> result = new HashSet<>();

    public static void main(String[] args) {
        String str = "abcd";
        boolean[] visit = new boolean[str.length()];
        doPreum(str, visit, 0, "");
        System.out.println(result);
        System.out.println(result.size());
    }

    private static void doPreum(String str, boolean[] visit, int start, String temp) {

        if (temp.length() == str.length()) {
            result.add(temp);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            if (visit[i]) {
                continue;
            }
            visit[i] = true;
            doPreum(str, visit, start + 1, temp + str.charAt(i));
            visit[i] = false;
        }

    }
}
