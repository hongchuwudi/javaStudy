package lanhan;

//  蓝韩式单例
public class A {
    //  私有化静态变量
    private static A a;

    private A() {
    }

    ;

    public static A getInstance() {
        if (a == null) {
            a = new A();
        }
        return a;
    }
}
