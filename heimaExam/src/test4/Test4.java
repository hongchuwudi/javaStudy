package test4;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test4 {
    public static void main(String[] args) {
        // 初始化商品列表（按题目顺序）
        List<Good> goods = new ArrayList<>();
        goods.add(new Good("水", 4, 24));
        goods.add(new Good("牛奶", 8, 160));
        goods.add(new Good("五粮液", 2, 4000));
        goods.add(new Good("可乐", 6, 108));
        goods.add(new Good("茅台", 1, 4000));

        final int CAPACITY = 10;

        // 动态规划数组：dp[j]表示j升容量时的最大价值
        int[] dp = new int[CAPACITY + 1];

        // 记录选择路径：selection[j]保存容量j时的物品组合
        Map<Integer, Map<String, Integer>> selection = new HashMap<>();
        for (int i = 0; i <= CAPACITY; i++)
            selection.put(i, new HashMap<>());


        // 动态规划求解
        for (Good good : goods) {
            for (int j = good.getWeight(); j <= CAPACITY; j++) {
                int newValue = dp[j - good.getWeight()] + good.getValue();
                if (newValue > dp[j]) {
                    dp[j] = newValue;

                    // 复制之前的配置并更新当前物品数量
                    Map<String, Integer> newConfig = new HashMap<>(selection.get(j - good.getWeight()));
                    newConfig.put(good.getName(), newConfig.getOrDefault(good.getName(), 0) + 1);
                    selection.put(j, newConfig);
                }
            }
        }

        // 输出结果
        System.out.println("最大总价值: " + dp[CAPACITY]);
        System.out.println("液体组合：");
        selection.get(CAPACITY).forEach((name, count) -> {
            Good g = goods.stream().filter(x -> x.getName().equals(name)).findFirst().get();
            System.out.printf("%s 取 %d 次（共%d升，价值%d）\n",
                    name, count,
                    count * g.getWeight(),
                    count * g.getValue());
        });
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Good {
    private String name;
    private int weight;    // 单次选取需要的升数
    private int value;     // 单次选取获得的价值
}