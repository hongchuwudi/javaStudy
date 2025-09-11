package interfaceimportantThing;

public class Test {
}

//  1.必须重写几接口的所有函数才能实例类,否则是抽象类
//  2.方法实现不能冲突名称
//  3.继承与接口实现冲突
//  4.一个类实现多个类的二义性问题
class D implements C {
    @Override
    public void show1() {
        System.out.println("show1");
    }

    @Override
    public void show2() {
        System.out.println("show2");
    }

    @Override
    public void show3() {
        System.out.println("show3");
    }

}
