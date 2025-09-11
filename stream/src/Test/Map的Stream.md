可以通过 `IntStream` 生成索引，将两个列表按索引位置一一映射为键值对。以下是实现代码：

```java
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("张忌");
        list.add("无忌");
        list.add("张无");
        list.add("张");
        list.add("忌");
        list.add("无");
        list.add(" ");

        List<Double> list2 = new ArrayList<>();
        list2.add(11.1);
        list2.add(22.2);
        list2.add(33.3);
        list2.add(44.4);
        list2.add(55.5);
        list2.add(55.5);
        list2.add(55.5);
        list2.add(55.5);

        // 核心代码：将两个列表按索引合并为 Map<String, Double>
        Map<String, Double> result = IntStream.range(0, Math.min(list.size(), list2.size()))
                .boxed()
                .collect(Collectors.toMap(
                        i -> list.get(i),  // 键：list 中的元素
                        i -> list2.get(i), // 值：list2 中的元素
                        (oldVal, newVal) -> newVal // 处理键冲突（如果 list 有重复元素）
                ));

        System.out.println(result);
    }
}
```

---

### **代码说明**

1. **生成索引流**：
    - `IntStream.range(0, Math.min(list.size(), list2.size()))` 生成索引范围，确保不超过较短列表的长度。

2. **转换为对象流**：
    - `.boxed()` 将 `IntStream` 转为 `Stream<Integer>`，便于后续操作。

3. **收集为 Map**：
    - `Collectors.toMap` 将索引映射为键值对：
        - **键**：`list.get(i)`（如 "张无忌"）
        - **值**：`list2.get(i)`（如 11.1）
        - **合并函数**：`(oldVal, newVal) -> newVal`（如果 `list` 中有重复键，保留最后一次出现的值）

---

### **输出结果**

```java
{ =55.5, 无=55.5, 忌=55.5, 张=55.5, 无忌=33.3, 张无=44.4, 张忌=22.2, 张无忌=11.1}
```

---

### **注意事项**

1. **键冲突处理**：
    - 如果 `list` 中有重复元素（如两个 "张无忌"），合并函数决定保留哪个值。示例中选择保留新值（`newVal`
      ），也可改为保留旧值（`oldVal`）。

2. **列表长度不一致**：
    - 使用 `Math.min(list.size(), list2.size())` 确保只处理共同索引范围内的元素，避免越界。

3. **有序性**：
    - 默认生成的 `Map` 是无序的（如 `HashMap`）。若需保持插入顺序，可改用 `LinkedHashMap`：
      ```java
      .collect(Collectors.toMap(
          i -> list.get(i),
          i -> list2.get(i),
          (oldVal, newVal) -> newVal,
          LinkedHashMap::new // 指定 Map 实现类
      ));
      ```