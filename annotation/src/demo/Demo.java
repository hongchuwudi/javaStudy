package demo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Demo {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        //  目标:搞清楚注解的应用场景 模拟junit框架 有Mytest注解的方法就执行
        //  获取类对象
        Demo d = new Demo();
        Class c = Demo.class;
        //  获取所有方法
        Method[] methods = c.getDeclaredMethods();
        //  遍历方法判断方法是否有@myTests有就执行没就不执行
        for (Method method : methods) {
            if (method.isAnnotationPresent(MyTests.class)) {
                MyTests myTests = method.getDeclaredAnnotation(MyTests.class);
                int count = myTests.count();
                for (int i = 0; i < count; ++i) method.invoke(d);
            }
        }

    }

    @MyTests
    public void testPrint1() {
        System.out.println("testPrint1执行了");
    }

    @MyTests
    public void testPrint2() {
        System.out.println("testPrint2执行了");
    }

    public void testPrint3() {
        System.out.println("testPrint3执行了");
    }

    public void testPrint4() {
        System.out.println("testPrint4执行了");
    }
}
