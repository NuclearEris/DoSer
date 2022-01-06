package org.sec;

import java.util.*;

@SuppressWarnings("all")
public class TestCollection {
    public static void main(String[] args) {
        testList(10);
        testListObj(new Obj(10));
        testMap(10);
        testMapObj(new Obj(10));
    }

    static class Obj {
        int data;

        Obj(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }
    }

    private static void testList(int data) {
        List<String> temp = new ArrayList<>(data);
        System.out.println(temp.size());
    }

    private static void testListObj(Obj obj) {
        List<String> temp = new ArrayList<>(obj.getData());
        System.out.println(temp.size());
    }

    private static void testMap(int data) {
        Map<String, String> map = new HashMap<>(data);
        System.out.println(map.size());
    }

    private static void testMapObj(Obj obj) {
        Map<String, String> map = new HashMap<>(obj.getData());
        System.out.println(map.size());
    }
}
