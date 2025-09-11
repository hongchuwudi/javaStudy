package staticInternalClass;

public class Test {
    //  搞清楚静态内部类的语法
    //  创建对象 外部类名.内部类名 = new 外部类名.内部类名();
}

class Outer {
    public static String schoolName;
    public int age;

    public static class Inner {
        private String name;

        //  成员
        public void show() {
            System.out.println(schoolName);
//            System.out.println(age);      //  静态内部类和实例内部类都无法访问外部类的成员
        }

    }
}
