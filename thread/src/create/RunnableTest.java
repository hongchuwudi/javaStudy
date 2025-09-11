package create;

public class RunnableTest {
    public static void main(String[] args) {
        //  目标:创建线程的方式:实现runnbale接口来创建
        Runnable myRunnable1 = new MyRunnable(1);
        MyRunnable myRunnable2 = new MyRunnable(2);

        Thread t1 = new Thread(myRunnable1);
        Thread t2 = new Thread(myRunnable2);

        t1.start();
        t2.start();

        for (int i = 0; i < 100; ++i) {
            System.out.println("主线程" + "输出: " + i);
        }
    }
}

class MyRunnable implements Runnable {
    int idx;

    public MyRunnable(int idx) {
        this.idx = idx;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; ++i) {
            System.out.println("子线程" + idx + "输出: " + i);
        }
    }
}
