package interfaceTest;

public interface A {
    //  jdk8 之前只能定义常量和抽象函数
    String SCHOOL_CODE = "140";

    //  接口中的抽象方法可以省略abstract关键字 省缺的情况下就是抽象方法
    public void fun1();   //  不能写 {}

    public void fun2();   //    可以有很多抽象函数

}
