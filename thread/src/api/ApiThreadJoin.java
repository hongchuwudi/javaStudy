package api;

public class ApiThreadJoin {
    public static void main(String[] args) {
        //  目标:搞清楚当前线程的join方法 这是一个线程插队的方法 让调用这个方法的线程先执行完毕
        Thread t1 = new Mythread3();
        t1.start();
        for (int i = 0; i < 10; ++i) {
            System.out.println(Thread.currentThread().getName() + "输出:" + i);
            try {
                if (i == 2) t1.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class Mythread3 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; ++i)
            System.out.println(Thread.currentThread().getName() + "输出:" + i);
    }
}
