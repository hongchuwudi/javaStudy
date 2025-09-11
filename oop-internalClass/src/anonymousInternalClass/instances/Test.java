package anonymousInternalClass.instances;

public class Test {
    public static void main(String[] args) {
        Swim s1 = new Swim() {
            @Override
            public void swimming() {
                System.out.println("yyy");
            }
        };
        oneday(s1);
    }

    public static void oneday(Swim s1) {
        System.out.println("开始");
        s1.swimming();
        System.out.println("结束");
    }
}

interface Swim {
    void swimming();        //  游泳方法
}
