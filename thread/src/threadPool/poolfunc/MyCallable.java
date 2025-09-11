package threadPool.poolfunc;

import java.util.concurrent.Callable;

//  定义接口并实现callable接口
public class MyCallable implements Callable<String> {
    private int n;

    public MyCallable(int n) {
        this.n = n;
    }

    public String call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 10; ++i) {
            sum += i;
        }
        return Thread.currentThread().getName() + " 计算 1-" + n + "的和是: " + sum;
    }
}
