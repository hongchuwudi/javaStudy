package create;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableTest {
    //  前两种方式创建的线程执行完run之后不会返回什么,所以需要返回的一种创建方式
    //  Callable 结合 futureTask 可以实现这一点要求
        /*
          1.  创建任务对象实现callable接口call方法封装要做的事情返回想要返回的数据
              把callable类型的对象封装成为futureTask献策会给你任务对象
          2.  把线程任务对象交给Thread
          3.  调用Thread 的 start()方法启动线程
          4.  通过 futureTask对象的get方法获取线程执行的结果
         */

    public static void main(String[] args) {
        //  错误写法 这样执行完一个线程才会执行第二个
//        createCallable(100, 1);
//        createCallable(100, 2);

//        Callable<String> c1 = new Mycallable(100,1);
//
//        FutureTask<String> f1 = new FutureTask<>(c1);   //  可以用多态 Callable
//        //  未来对象的作用:
//        //  本质是一个runnable对象
//        Thread t = new Thread(f1);
//        t.start();
//
//        //  获取线程执行完毕后的返回结果
//        try {
//            System.out.println(f1.get());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        Callable<String> c2 = new Mycallable(100, 2);
//
//        FutureTask<String> f2 = new FutureTask<>(c2);   //  可以用多态 Callable
//        //  未来对象的作用:
//        //  本质是一个runnable对象
//        Thread t2 = new Thread(f2);
//        t2.start();
//
//        //  获取线程执行完毕后的返回结果
//        try {
//            System.out.println(f2.get());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // 创建并启动线程1
        FutureTask<String> f1 = createAndStartThread(100, 1);
        // 创建并启动线程2
        FutureTask<String> f2 = createAndStartThread(100, 2);

        // 统一获取结果（此时两个线程已在并发执行）
        try {
            System.out.println(f1.get());
            System.out.println(f2.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createCallable(int n, int idx) {
        Callable<String> c = new Mycallable(n, idx);

        FutureTask<String> f = new FutureTask<>(c);   //  可以用多态 Callable
        //  未来对象的作用:
        //  本质是一个runnable对象
        Thread t = new Thread(f);
        t.start();

        //  获取线程执行完毕后的返回结果
        try {
            System.out.println(f.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 封装线程创建和启动逻辑
    private static FutureTask<String> createAndStartThread(int n, int idx) {
        Callable<String> c = new Mycallable(n, idx);
        FutureTask<String> f = new FutureTask<>(c);
        Thread t = new Thread(f);
        t.start();
        return f;
    }
}

//  1.定义实现一个callable的类
class Mycallable implements Callable<String> {
    private final int n;
    private final int idx;

    public Mycallable(int n, int idx) {
        this.n = n;
        this.idx = idx;
    }

    //  实现call方法 定义线程的执行体
    public String call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100; ++i) {
            System.out.println("子线程" + idx + ": " + i);
            sum += i;
        }
        return "子线程计算完毕! 结果是" + sum;
    }
}


