package memberInternalClass;

public class Test {
    public static void main(String[] args) {
        //  成员内部类的语法
        //  本质是类的成员
        Outer.Inner oi = new Outer().new Inner();
    }
}
