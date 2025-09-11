package api;

public class APiTest2 {
    public static void main(String[] args) {

        Thread t1 = new MyThread2("1号线程");
        System.out.println(t1.getName());
        t1.start();

        Thread t2 = new MyThread2("2号线程");
        System.out.println(t2.getName());       //  线程默认名称是线程-索引
        t2.start();

        Thread m = Thread.currentThread();      //主线程
        m.setName("主线程");
        System.out.println(m.getName());
    }
}

class MyThread2 extends Thread {
    public MyThread2(String name) {
        //  调用父类的有参构造器
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; ++i)
            System.out.println(this.getName() + " : " + i);
    }
}
