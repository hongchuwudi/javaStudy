package safeThreads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String cardId;  //  卡号
    private double money;   //  余额

    public void drawMoney(double money) {
        //  拿到当前取钱的人
        String name = Thread.currentThread().getName();
        if (this.money >= money) {
            System.out.println(name + "取" + money + "元成功!");
            this.money -= money;
            System.out.println(name + " 当前余额: " + this.money);
        } else {
            System.out.println(name + "取" + money + "元失败!" + " 当前余额账户不足: " + this.money);
        }
    }
}
