package getClass;

public class TestGetClass {
    public static void main(String[] args) throws ClassNotFoundException {
        //  目标:掌握反射第一步操作 :获取class对象

        //  1.获取类对象本身 类.class
        Class c1 = Student.class;
        System.out.println(c1);

        //  2.获取类本身 class.forName("类的全名")
        Class c2 = Class.forName("getClass.Student");
        System.out.println(c2);

        //  3.获取类本身 对象.getClass()
        Student s3 = new Student();
        Class c3 = s3.getClass();
        System.out.println(c3);

        System.out.println(c1 == c2);           //  同一对象
        System.out.println(c2.equals(c3));      //
    }
}
