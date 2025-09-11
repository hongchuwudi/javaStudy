package ehan;

//  单例只能创建一个对象 静态变量只做一件事情 用静态变量标识当前类的对象数量
public class A {
    int numa;
    public static A a = new A(111);

    private A(int numa) {
        this.numa = numa;
    }

}