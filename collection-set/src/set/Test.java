package set;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
        //  认识set集合的特点
        //  无序 不重复 无索引
        Set<String> set = new HashSet<>();
        set.add("hongmeng");
        set.add("xiaohu");
        set.add("hongmeng");
        //  TreeSet集合 排序(默认一定要大小写升序排序)  不重复,无索引
        Set<Double> set1 = new TreeSet<>();
        set1.add(1.1);
        set1.add(2.2);
        set1.add(3.3);
        set1.add(4.4);
        System.out.println(set1);
    }
}
