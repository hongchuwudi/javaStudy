package threadPool.poolfunc;

import java.util.concurrent.*;

public class MyRunableTest {
    public static void main(String[] args) {
        // 目标:线程池实现
        //  1.使用线程池的实现类ThreadPoolExecutor声明七个参数来创建线程池对象
        ExecutorService pool = new ThreadPoolExecutor(
                3,                             //  核心线程数量
                5,                                          //  最大线程数量
                10,                                         //  空闲线程存活时间
                TimeUnit.SECONDS,                           //  存活时间单位
                new ArrayBlockingQueue<>(3),      //  制定线程的任务队列
                Executors.defaultThreadFactory(),           //  线程池的线程工厂
                new ThreadPoolExecutor.AbortPolicy()        //  线程池的拒绝策略
                //  任务拒绝策略
                //  1.AbortPolicy           丢弃并抛出异常
                //  2.discardPolicy         丢弃任务但不抛出(最不推荐)
                //  3.discardOldestPolicy   任务队列最久任务抛弃原则
                //  4.callerrunsPolicy      主线程负责调用任务的run方法从而绕过线程池直接执行(主线程接管任务)
        );

        //  2.线程池处理任务的方法:
        //    (1).runnable
        //    线程池正常情况下自动创建并启动线程
        //    临时线程的创建时机:
        //      1.所有核心线程在忙
        //      2.任务队列全满
        //      3.还可以创建新线程
        Runnable target = new MyRunnable();
        //  核心线程处理
        pool.execute(target);
        pool.execute(target);
        pool.execute(target);
        //  任务队列排队
        pool.execute(target);
        pool.execute(target);
        pool.execute(target);
        //  临时线程处理
        pool.execute(target);
        pool.execute(target);
        //  线程池拒绝任务
        pool.execute(target);


        //  3.关闭线池
//        pool.shutdown();
    }
}
