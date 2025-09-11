package api;

public class ApiThreadSleep {
    public static void main(String[] args) {
        for (int i = 0; i < 10; ++i) {
            System.out.println(i);
            try {
                //  让当前执行的线程进入休眠状态  直到时间到了,才会继续执行
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
