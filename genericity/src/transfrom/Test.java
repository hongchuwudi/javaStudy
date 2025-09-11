package transfrom;

import java.util.ArrayList;

public class Test {
    //  java泛型编译后消除泛型转化为 Object
    public static void main(String[] args) {
        //  写法错误 必须是内置类型的类 --- 包装类
//        ArrayList<int> list = new ArrayList<int>();
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Byte> list2 = new ArrayList<>();

    }
}
