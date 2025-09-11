package Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class IntermediateFunc {
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

        list.stream().filter(s -> s.startsWith("张")).sorted(String::compareTo).forEach(System.out::println);
        System.out.println("-------------------");

        List<Double> list2 = new ArrayList<>();
        list2.add(11.1);
        list2.add(22.2);
        list2.add(33.3);
        list2.add(44.4);
        list2.add(55.5);
        list2.add(55.5);
        list2.add(55.5);
        list2.stream().sorted().forEach(System.out::println);
        System.out.println("-------------------");
        //  排序
        list2.stream().sorted((s1, s2) -> Double.compare(s1, s2)).forEach(System.out::println);
        System.out.println("-------------------");
        list2.stream().sorted((s1, s2) -> Double.compare(s2, s1)).forEach(System.out::println);
        System.out.println("-------------------");
        list2.stream().sorted(Double::compare).forEach(System.out::println);
        System.out.println("-------------------");
        //  跳过    skip
        list2.stream().skip(2).forEach(System.out::println);
        System.out.println("-------------------");
        //  映射/加工方法
        list2.stream().map(s -> "加工后 :" + (s + 10)).forEach(System.out::println);
        System.out.println("-------------------");
        //  去重重写对象的hashCode 和 equal方法才可以去重
        list2.stream().sorted((s1, s2) -> Double.compare(s2, s1)).distinct().forEach(System.out::println);
        System.out.println("-------------------");

        //  合并流streams
        Stream<String> s1 = Stream.of("张三", "张思", "账务", "张柳", "张器");
        Stream<Integer> s2 = Stream.of(1, 2, 4, 5, 6);
        Stream<Object> s3 = Stream.concat(s1, s2);
        s3.forEach(System.out::println);
    }
}
