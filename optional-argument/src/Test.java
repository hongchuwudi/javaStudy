public class Test {
    public static void main(String[] args) {
        //  可变参数
        sum1();
        sum1(1);
        //  sum()   报错
        sum2(1);
    }

    //  任意参数
    public static void sum1(int... args) {

    }

    //  至少一个
    public static void sum2(int arg1, int... args) {

    }
}
