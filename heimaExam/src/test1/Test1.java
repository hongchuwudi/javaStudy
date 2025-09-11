package test1;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/*
需求:
    ·某双色球系统，红球是1-35之间的数据，篮球是1-15之间的数据，一注双色球号码是由6个不重复的号码和1个篮球号码组成的
     10 12 30 16 7 17 12
具体功能点的要求如下：
    ·请随机一组双色球号码，6个红球号码要求不重复，且升序排序输出，篮球号码放在最后面输出。
    ·假设上图展示的是中奖号码，请用程序判断出第一个功能随机出的双色球号码中了几个红球和几个篮球
 */
public class Test1 {
    public static void main(String[] args) {
        //  TreeSet 可以去重排序

        //  1.为用户随机生成 7 个数字
        Set<Integer> red = new TreeSet<>();
        while (red.size() < 6) {
            int num = (int) (Math.random() * 35 + 1);
            red.add(num);
        }
        System.out.println("用户的红球号码" + red);
        int blue = (int) (Math.random() * 15 + 1);
        System.out.println("用户的蓝球号码" + blue);

        //  2.定义中奖号码的集合
        Set<Integer> luckRed = new TreeSet<>();
        Collections.addAll(luckRed, 10, 12, 30, 6, 7, 17);
        int luckBlue = 12;

        //  3.判断用户是否中奖
        int redCount = 0;
        int blueCount;
        for (Integer num : red)
            redCount += luckRed.contains(num) ? 1 : 0;
        blueCount = blue == luckBlue ? 1 : 0;

        //  打印结果
        System.out.println("用户中了" + redCount + "个红球");
        System.out.println("用户中了" + blueCount + "个蓝球");
    }

}
