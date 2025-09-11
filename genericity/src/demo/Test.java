package demo;

import java.util.ArrayList;

//  E K V N T SUV
public class Test {
    public static void main(String[] args) {
        //  泛型
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<Integer> list2 = new ArrayList<>();
    }
}

class MyArrayList<E> {
    private final ArrayList<Object> list = new ArrayList<>();
    private E inta;

    //  泛型构造器
    public MyArrayList(E value) {
        this.inta = value;
    }

    //  普通函数
    public E getInta() {
        return this.inta;
    }

    //  泛型方法
    public <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.println(element + " ");
        }
    }

    //  静态泛型方法
    public static <T> void printArrays(T[] array) {
        for (T element : array) {
            System.out.println(element + " ");
        }
    }
}

class shixianclass implements demoInterface<Integer> {
    public Integer next() {
        return null;
    }

}