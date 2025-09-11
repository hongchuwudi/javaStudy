package create;

public class ThreadTest {
    public static void main(String[] args) {
        //  目标:认识多线程 什么是线程 掌握创建线程的 方式之一 继承thread 类来实现

        //  3.创键一个线程的对象代表线程
        Thread t1 = new MyThread();
        t1.start();

        for (int i = 0; i < 100; ++i)
            System.out.println("主线程: " + i);
    }

}

//  1.定义一个子类继承thread类 ,称为一个线程类
class MyThread extends Thread {
    //  2.重写线程的run方法
    @Override
    public void run() {
        for (int i = 0; i < 100; ++i)
            System.out.println("子线程:  " + i);
    }
}
