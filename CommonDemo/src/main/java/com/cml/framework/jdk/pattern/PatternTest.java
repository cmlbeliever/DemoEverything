package com.cml.framework.jdk.pattern;

import java.util.regex.Pattern;

/**
 * @Auther: cml
 * @Date: 2018-08-01 18:09
 * @Description:
 */
public class PatternTest {
    public static void main(String[] args) {
        String value = "Sean#AQTYD89834#ce";
        String value1 = "Sean#AQTYD89834#c";
        String value2 = "Sean#AQTYD89834#v";

        System.out.println(Pattern.matches("Sean#.*?#c", value));
        System.out.println(Pattern.matches("Sean#.*?#c", value1));
        System.out.println(Pattern.compile("Sean#.*#c").matcher(value2).find());
        System.out.println("=====");
        System.out.println(Pattern.compile("Sean#.*?#c").matcher(value).matches());
        System.out.println(Pattern.compile("Sean#.*?#c").matcher(value1).matches());

        System.out.println(Pattern.compile("Sean#.*?#c").matcher(value).find());
        System.out.println(Pattern.compile("Sean#.*?#c").matcher(value1).find());
    }
}
