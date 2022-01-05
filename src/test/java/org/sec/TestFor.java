package org.sec;

@SuppressWarnings("all")
public class TestFor {
    public static void main(String[] args) {
        testFor(10);
        testForObj(new Obj(10));
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

    private static void testFor(int data) {
        for (int i = 0; i < data; i++) {
            System.out.println("test");
        }
    }

    private static void testForObj(Obj obj) {
        int data = obj.getData();
        for (int i = 0; i < data; i++) {
            System.out.println("test");
        }
    }
}
