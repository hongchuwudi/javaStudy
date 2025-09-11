package create;

public class InternaclassTest {

    public static void main(String[] args) {
        //  目标:创建线程的方式:实现runnbale匿名内部类接口来创建
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; ++i)
                    System.out.println("子线程1:" + i);

            }
        };
        Thread t1 = new Thread(r);
        t1.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; ++i)
                    System.out.println("子线程2:" + i);
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100; ++i)
                System.out.println("子线程3:" + i);
        }).start();

    }
}


