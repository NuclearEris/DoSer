package org.sec;

@SuppressWarnings("all")
public class TestArray {
    public static void main(String[] args) {
        testArray(10);
        testArrayObj(new Obj(10));
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

    private static void testArray(int data) {
        byte[] array = new byte[data];
        System.out.println(array.length);
    }

    private static void testArrayObj(Obj obj) {
        byte[] array = new byte[obj.getData()];
        System.out.println(array.length);
    }
}
