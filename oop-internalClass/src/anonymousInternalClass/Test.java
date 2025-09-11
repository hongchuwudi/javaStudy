package anonymousInternalClass;

public class Test {
    //  匿名内部类   - 对标Lamdab表达式 声明即调用
    //  本质是一个子类 同时也是一个子类对象
    Animal a = new Animal() {
        @Override
        public void cry() {
            System.out.println("fajhfa");
        }
    };
    Animal b = new Animal() {
        @Override
        public void cry() {
            System.out.println("fa");
        }
    };
}
