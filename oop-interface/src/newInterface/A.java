package newInterface;

public interface A {
    //  以下三种方法属于库实现技术
    //  1.默认方法(普通实例方法) 必须default修饰
    //  默认会使用public修饰

    //  函数式编程默认方法
    default void go() {
        System.out.println("gogogo");
    }

    //  2.私有化方法(jdk9+)
    //  私有化实例方法 -使用接口中的其他实例来调用它
    private void run() {
        System.out.println("runrunrun");
    }

    //  3.静态方法
    //  只能使用当前接口调用该静态方法,静态方法属于接口不会被继承
    static void show() {
        System.out.println("showshowshow");
    }
}
