package threadPool.poolfunc;

import java.util.concurrent.*;

public class MycallableTest {
    public static void main(String[] args) {
        ExecutorService pool = new ThreadPoolExecutor(
                3,
                5,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        //  添加任务
        Future<String> f1 = pool.submit(new MyCallable(100));
        Future<String> f2 = pool.submit(new MyCallable(100));
        Future<String> f3 = pool.submit(new MyCallable(100));
        Future<String> f4 = pool.submit(new MyCallable(100));

        try {
            System.out.println(f1.get());
            System.out.println(f2.get());
            System.out.println(f3.get());
            System.out.println(f4.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
