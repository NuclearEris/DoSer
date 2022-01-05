package org.sec;

import java.util.regex.Pattern;

@SuppressWarnings("all")
public class TestPattern {
    public static void main(String[] args) {
        testPattern(".*", "test");
        testPatternObj(new Obj(".*", "test"));
    }

    static class Obj {
        String regex;
        String data;

        Obj(String regex, String data) {
            this.regex = regex;
            this.data = data;
        }

        public String getData() {
            return data;
        }

        public String getRegex() {
            return regex;
        }
    }

    private static void testPattern(String regex, String data) {
        if (Pattern.matches(regex, data)) {
            System.out.println("test");
        }
    }

    private static void testPatternObj(Obj obj) {
        String regex = obj.getRegex();
        String data = obj.getData();
        if (Pattern.matches(regex, data)) {
            System.out.println("test");
        }
    }
}
