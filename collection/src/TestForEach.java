import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class TestForEach {
    public static void main(String[] args) {
        //  目的一:迭代器
        Collection<String> names = new ArrayList<>();
        names.add("张无忌");
        names.add("张忌");
        names.add("张无");
        names.add("无忌");
        names.add("张");
        names.add("忌");
        names.add("无");
        names.add("  ");
        //  直接取数据
        //  iterotar 默认是第一个元素 指向数组索引0
        Iterator<String> it = names.iterator();
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
        System.out.println(it.next());
        //  循环迭代
        while (it.hasNext()) {
            String ele = it.next();
            System.out.println(it.next());
        }

        //  目的二:增强for 基于范围的for
        for (String name : names) {
            System.out.println(name);
        }

        //  目的三:forEach - lamdab
        names.forEach(s -> System.out.println(s));
        names.forEach(System.out::println);
    }
}
