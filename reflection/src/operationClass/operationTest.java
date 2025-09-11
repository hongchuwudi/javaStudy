package operationClass;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class operationTest {
    @Test
    public void GetClassInfo() {
        System.out.println("=====获取名称=====");
        Class c1 = Student.class;
        System.out.println(c1.getName());
        System.out.println(c1.getSimpleName());
        System.out.println("------------------------------------------------------");
    }

    @Test
    public void Constructor() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //  无论是私有还是共有 照拿不误
        System.out.println("=====一次性获取所有的构造器=====");
        //  一次性获取所有的构造器-数组
        Class c1 = Student.class;
        Constructor[] cons = c1.getDeclaredConstructors();
        for (Constructor con : cons)
            System.out.println(con.getName() + "(" + con.getParameterCount() + ")");

        System.out.println("------------------------------------------------------");
        System.out.println("=====拿单个构造器=====");
        //  拿单个构造器
        //  有参构造器
        Constructor con1 = c1.getDeclaredConstructor();     //  无参
        System.out.println(con1.getName() + "(" + con1.getParameterCount() + ")");

        //  无参构造器
        Constructor con2 = c1.getDeclaredConstructor(String.class, int.class);     //  有参
        System.out.println(con2.getName() + "(" + con2.getParameterCount() + ")");

        System.out.println("------------------------------------------------------");
        System.out.println("=====构造器操作使用:创建对象=====");
        //  暴力反射:直接访问私有的变量,方法,构造器等;
        con1.setAccessible(true);
        Student s1 = (Student) con1.newInstance();
        System.out.println(s1);
        System.out.println("------------------------------------------------------");

    }

    @Test
    public void GetFieldInfo() throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
        //  目标:拿到类的成员变量对象并对其进行操作
        System.out.println("=====拿到类的成员变量对象=====");
        Class c1 = Student.class;

        //  获取成员变量对象
        //  字段/属性 field
        Field[] fields = c1.getDeclaredFields();
        for (Field field : fields)
            System.out.println(field.getName() + "(" + field.getType().getName() + ")");


        //  获取单个字段的对象 成员变量对象
        Field field1 = c1.getDeclaredField("age");
        System.out.println(field1.getName() + "(" + field1.getType().getName() + ")");

        Field field2 = c1.getDeclaredField("name");
        System.out.println(field2.getName() + "(" + field2.getType().getName() + ")");

        System.out.println("------------------------------------------------------");
        System.out.println("=====操作成员变量=====");
        Student s2 = new Student("fafafafa", 1);
        System.out.println("反射之前" + s2.getName());
        field2.setAccessible(true);
        String namefle = (String) field2.get(s2);
        field2.set(s2, "ddddd");
        System.out.println("反射之后" + s2.getName());
        System.out.println("------------------------------------------------------");

    }

    @Test
    public void getMethodInfo() throws Exception {
        //  目标: 获取类的成员方法对象 并对其进行操作
        System.out.println("=====获取类的成员方法对象=====");
        Class c1 = Student.class;

        Method[] methods = c1.getDeclaredMethods();
        for (Method method : methods)
            System.out.println(method.getName() + "(" + method.getParameterCount() + ")");

        Method m1 = c1.getDeclaredMethod("eat");
        Method m2 = c1.getDeclaredMethod("eat", String.class);
        System.out.println(m1.getName() + "(" + m1.getParameterCount() + ")");
        System.out.println(m2.getName() + "(" + m2.getParameterCount() + ")");
        System.out.println("------------------------------------------------------");
        System.out.println("=====操作类的成员方法对象=====");

        Student s3 = new Student("gfhasdk;lga", 3);
        m1.setAccessible(true); //  绕过访问权限 直接访问!
        m1.invoke(s3);      //唤醒对象s3的eat方法执行 相当于d.eat()

        System.out.println("------------------------------------------------------");
    }
}
