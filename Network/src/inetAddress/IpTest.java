package inetAddress;

import java.net.InetAddress;

public class IpTest {
    public static void main(String[] args) {
        //  目标: 认识inetAddress 获取本机ip地址和对方ip对象
        try {
            //  获取本机ip地址
            InetAddress localIp4 = InetAddress.getLocalHost();
            //  打印 主机名 和 ip 地址
            System.out.println(localIp4);
            System.out.println(localIp4.getHostName() + " " + localIp4.getHostAddress());
            //  打印

            //  获取 对方ip对象
            InetAddress baiduIp4 = InetAddress.getByName("www.baidu.com");
            System.out.println(baiduIp4);

            System.out.println(localIp4.isLoopbackAddress());        //  是否是回环地址
            System.out.println(localIp4.isLinkLocalAddress());
            System.out.println(localIp4.isReachable(100));  //  检查是否与此ip连通
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
