package test2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {

        // 生成1-200的数值列表并打乱顺序
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 200; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        // 取前100个不重复的编号作为囚犯ID，并分配初始位置
        List<Prisoner> prisoners = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int id = numbers.get(i);
            int firstLocation = i + 1; // 初始位置从1开始
            prisoners.add(new Prisoner(id, firstLocation));
        }

        // 循环淘汰奇数位置的囚犯
        List<Prisoner> currentList = new ArrayList<>(prisoners);
        while (currentList.size() > 1) {
            List<Prisoner> nextList = new ArrayList<>();
            for (int i = 0; i < currentList.size(); i++) {
                // 当前位置为i+1，偶数位置保留
                if ((i + 1) % 2 == 0) {
                    nextList.add(currentList.get(i));
                }
            }
            currentList = nextList;
        }

        // 输出幸存者信息
        Prisoner survivor = currentList.get(0);
        System.out.println("幸存者编号：" + survivor.getId() + "，首次位置：" + survivor.getFirstLocation());
    }
}

@NoArgsConstructor
@Data
@AllArgsConstructor
class Prisoner {
    private int id;             // 编号
    private int firstLocation;  // 首次位置
}