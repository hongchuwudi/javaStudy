package demo.redbao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //  目标：完成多线程的综合小案例
        //  红包雨游戏，某企业有100名员工，员工的工号依次是1，2，3，4，.到100。
        //  现在公司举办了年会活动，活动中有一个红包雨环节，要求共计发出200个红包雨。其中小红包在[1－30]元之间，
        //  总占比为80%，大红包[31-100]元，总占比为20%。
        //  分析：100个员工实际上就是100个线程，来竞争200个红包。

        //  2.创建线程类 实现100个线程 竞争同一个集合
        List<Double> redbao = getRedBao();
        for (int i = 1; i <= 100; ++i) {
            new PeopleGetRedBao(redbao, "人" + i).start();
        }
    }

    //  1.准备200个大红包放到list中去
    public static List<Double> getRedBao() {
        Random r = new Random();
        List<Double> redBao = new ArrayList<>();
        for (int i = 0; i < 80; ++i) {
            redBao.add(r.nextDouble(30) + 1);
        }
        for (int i = 0; i < 20; ++i) {
            redBao.add(r.nextDouble(70) + 31);
        }
        return redBao;
    }
}
