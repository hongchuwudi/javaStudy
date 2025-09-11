package effect;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestReflectEffect {
    public static void main(String[] args) throws Exception {
        //  目标:反射的基本作用
        //  1.类的全部成分获取
        //  2.可以破坏封装性
        //  3.可以绕过泛型的约束
        ArrayList<String> list = new ArrayList<>();
        list.add("1111");
        list.add("2222");
        list.add("3333");
        list.add("4444");

        Class c1 = list.getClass(); //  c1 == ArrayLis.class
        //  获取ArrayList 的类add()
        Method add = c1.getDeclaredMethod("add", Object.class);

        //  可以加其他数据类型的add
        add.invoke(list, 9.9);
        add.invoke(list, true);

        System.out.println(list);

    }
}
