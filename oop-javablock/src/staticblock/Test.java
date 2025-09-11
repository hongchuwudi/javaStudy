package staticblock;

public class Test {
    //  1.静态代码块 有static修饰属于类,与类一起执行
    //  2.实例代码块 每次创建执行,和构造器很像 格式就是{}
    //  相当于vue的beforeCreate声明周期钩子初始化数据
    public static String[] cards = new String[54];

    //  静态代码块跟更加优雅,代码可读性健壮性更高
    static {
        System.out.println("test初始化");
    }

    public static void main(String[] args) {

    }
}
