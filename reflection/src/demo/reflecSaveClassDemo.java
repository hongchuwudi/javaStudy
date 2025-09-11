package demo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

public class reflecSaveClassDemo {

    public static void main(String[] args) throws FileNotFoundException, IllegalAccessException {
        Teacher t = new Teacher("小红", 19, "java、前端、动漫", 3000.0, "422期", '女', "12345678901");
        savaObject(t);
    }

    public static void savaObject(Object obj) throws FileNotFoundException, IllegalAccessException {
        PrintStream ps = new PrintStream(new FileOutputStream("D:\\01_program\\05_java\\intellij idea projects\\javaStudy1\\reflection\\src\\demo\\temp.txt", true));
        //  obj 可能是学生 老师 狗
        //  只有反射可以知道对象有多少个类

        //  1.获取Class对象
        Class c = obj.getClass();
        ps.println("===========" + c.getSimpleName() + "===========");

        //  2.获取成员属性字段
        Field[] fields = c.getDeclaredFields();

        //  3.遍历所有字段
        for (Field field : fields) {
            //  4.获取所有的字段 和 值
            field.setAccessible(true);
            String filename = field.getName();           //  获取字段名称
            Object fieldValue = field.get(obj) + "";     //  获取字段的值
            ps.println(filename + " : " + fieldValue);
        }

        ps.close();
    }
}

