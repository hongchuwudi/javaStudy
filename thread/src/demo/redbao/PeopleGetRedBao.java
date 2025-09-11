package demo.redbao;

import java.util.List;

public class PeopleGetRedBao extends Thread {
    private final List<Double> redBao;
    private int num;

    public PeopleGetRedBao(List<Double> redBao, String name) {
        super(name);
        this.redBao = redBao;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        num = 0;
        //  100个人来抢红包 redBao集合中的钱
        while (true) {
            synchronized (redBao) {
                if (redBao.isEmpty() || num == 5) break;

                int idx = (int) (Math.random() * redBao.size());
                Double money = redBao.remove(idx);
                num++;
                System.out.println(name + "抢到了" + money + "元");

            }
        }
    }
}
