package Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class EndTotalStreamFunc {
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
//        Optional 是 Java 8 引入的一个容器类，用于优雅地处理可能为 null 的值，避免直接判空导致的 NullPointerException。
//        它的核心思想是显式地标记“值可能不存在”，强制开发者主动处理空值情况
//        1. Optional 的作用
//        明确语义：表示一个值“可能有”或“可能没有”（如查询结果、计算值）
//        避免空指针：通过封装 null，提供安全的访问方法
//        链式调用：支持函数式风格的操作（如 map、filter）

        //  获取最高薪水
        Optional<String> maxname = list.stream().max(String::compareTo);
        System.out.println(maxname.get());
        Optional<String> minname = list.stream().min(String::compareTo);
        System.out.println(minname.get());
        // 终结方法 min max foeach


        //  收集方法有

        //  收集到list
        Stream<String> s1 = list.stream().filter(s -> s.startsWith("张"));
        List<String> lsittemp = s1.collect(Collectors.toList());
        System.out.println(lsittemp);
        //  收集到set
        Stream<String> s2 = list.stream().filter(s -> s.startsWith("张"));
        Set<String> settemp = s2.collect(Collectors.toSet());
        System.out.println(settemp);
        //  收集到数组中去
        Stream<String> s3 = list.stream().filter(s -> s.startsWith("张"));


        //  收集到map中去
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
