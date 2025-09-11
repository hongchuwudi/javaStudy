package threadPool.create;

import java.util.concurrent.*;

public class ExecutorServiceTest {
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
        );

        //  线程池处理任务的方法:
    }
}
