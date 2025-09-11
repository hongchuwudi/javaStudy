import java.util.ArrayList;
import java.util.Iterator;

public class TestForEach2 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        list.add("java入门");
        list.add("删除枸杞");
        list.add("加入黑枸杞");
        list.add("java菜鸟");
        list.add("删除白枸杞");
        list.add("加入录枸杞");
        list.add("java如图");
        list.add("删除黑枸杞");
        list.add("加入澜枸杞");
        list.add("java进界");
        list.add("删除录枸杞");
        list.add("加入白枸杞");
        System.out.println(list);
        list.sort(String::compareTo);
        //  删除全部枸杞(正向删除要i--解决并发异常)
        for (int i = 0; i < list.size(); ++i) {
            String name = list.get(i);
            if (name.contains("枸杞")) {
                list.remove(name);
                i--;
            }
        }
        //  删除全部枸杞(倒向删除)
        for (int i = list.size() - 1; i >= 0; --i) {
            String name = list.get(i);
            if (name.contains("枸杞")) list.remove(name);

        }
        System.out.println(list);
        System.out.println("------------------------------------------");
        //  利用迭代器的官方封装i--性质
        Iterator<String> it = list.iterator();

        while (it.hasNext()) {
            String name = it.next();
            if (name.contains("枸杞")) it.remove();
        }
        System.out.println(list);

    }
}
