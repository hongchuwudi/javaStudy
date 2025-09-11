package wildcard;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        //  通配符 ? 上下限
        //  需求 开发一个极品飞车游戏
        ArrayList<Xioami> xioamis = new ArrayList<>();
        xioamis.add(new Xioami());
        xioamis.add(new Xioami());
        xioamis.add(new Xioami());
        xioamis.add(new Xioami());

        ArrayList<BYDcar> byds = new ArrayList<>();
        byds.add(new BYDcar());
        byds.add(new BYDcar());
        byds.add(new BYDcar());
        byds.add(new BYDcar());
        //  虽然xioami 和 byd 是 car 的子类 但是arrayList<xioami> arrayList<BYDcar> 没有关系
    }

    //  ? extends Number 接受Number及其子类 --- 上界
    public static void sgo(ArrayList<? extends Car> cars) {

    }

    //  ? super Number 接受Number及其父类 --- 下界
    public static void sout(ArrayList<? super Su7> cars) {

    }
}
