package api;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ApiTest {
    public static void main(String[] args) {
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

    // 封装线程创建和启动逻辑
    private static FutureTask<String> createAndStartThread(int n, int idx) {
        Callable<String> c = new Mycallable(n, idx);
        FutureTask<String> f = new FutureTask<>(c);
        Thread t = new Thread(f);
        t.setName(idx + "号线程");
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

