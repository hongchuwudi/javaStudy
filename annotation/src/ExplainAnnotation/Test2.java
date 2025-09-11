package ExplainAnnotation;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Test2 {
    public static void main(String[] args) {
        // 解析类上的注解
        Class<?> c2 = Tests.class;
        if (c2.isAnnotationPresent(MyTest2.class)) {
            MyTest2 myTest2 = (MyTest2) c2.getDeclaredAnnotation(MyTest2.class);
            String[] address = myTest2.address();
            double height = myTest2.height();
            String value = myTest2.value();
            System.out.println("类上的注解属性：");
            System.out.println(Arrays.toString(address) + "\n" + height + "\n" + value);
        }

        // 解析方法上的注解
        new Test2().parseMethod();
    }

    public void parseMethod() {
        try {
            Method method = Tests.class.getMethod("main", String[].class);
            if (method.isAnnotationPresent(MyTest2.class)) {
                MyTest2 myTest2 = method.getAnnotation(MyTest2.class);
                String[] address = myTest2.address();
                double height = myTest2.height();
                String value = myTest2.value();
                System.out.println("方法上的注解属性：");
                System.out.println(Arrays.toString(address) + "\n" + height + "\n" + value);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}