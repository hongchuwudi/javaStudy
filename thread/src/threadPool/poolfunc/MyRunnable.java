package threadPool.poolfunc;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; ++i) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
