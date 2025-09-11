package mutexLock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String cardId;  //  卡号
    private double money;   //  余额

    private final Lock lk = new ReentrantLock();      // 锁  final保护锁 防撬


    public void drawMoney(double money) {
        //  拿到当前取钱的人
        String name = Thread.currentThread().getName();

        //  余额判断逻辑
        //  this 只锁当前账户 account.class 锁静态文件

        //  上锁保护
        lk.lock();
        try {
            if (this.money >= money) {
                System.out.println(name + "取" + money + "元成功!");

                this.money -= money;

                System.out.println(name + "当前余额(元): " + this.money);
            } else {

                System.out.println(name + "取" + money + "元失败!");
                System.out.println(name + "当前余额(元): " + this.money);
            }
        } finally {
            //  解锁
            lk.unlock();
        }
    }
}
