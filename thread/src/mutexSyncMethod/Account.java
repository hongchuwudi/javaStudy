package mutexSyncMethod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String cardId;  //  卡号
    private double money;   //  余额

    public void printA() {
        System.out.println("当前公共账户: ");
        System.out.println("余额: " + this.money + "元");
    }

    public void drawMoney(double money) {
        //  拿到当前取钱的人
        String name = Thread.currentThread().getName();

        //  余额判断逻辑
        //  this 只锁当前账户 account.class 锁静态文件
        synchronized (this) {
            if (this.money >= money) {
                System.out.println(name + "取" + money + "元成功!");

                this.money -= money;

                System.out.println(name + " 当前余额: " + this.money);
            } else {

                System.out.println(name + "取" + money + "元失败!" + " 当前余额账户不足: " + this.money);

            }
        }
    }

    //    account.class 锁静态文件
    public static void drawMoney2(double money) {
        synchronized (Account.class) {

        }
    }
}
