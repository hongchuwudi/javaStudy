package proxyDemo;

public class Test {
    public static void main(String[] args) {
        Star star = new Star("xxx", "xxx");

        StarAction proxy = ProxyUtil.createProxy(star);

        proxy.sing("红赵元");
        System.out.println(proxy.dance());
    }
}
