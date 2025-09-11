package UDPprogram.Test1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPserver {
    public static void main(String[] args) throws Exception {
        System.out.println("===UDPserver===");
        //  目标:创建一个目标服务器 用于接受客户端发送的请求

        //  1.创建接受端对象 注册端口
        DatagramSocket socket = new DatagramSocket(8080);

        while (true) {
            //  2.创建数据包接收数据
            byte[] buf = new byte[1024 * 64];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);

            //  3.接收数据,
            socket.receive(packet);

            //  4.看看数据是否收到了
            String data = new String(buf);
            System.out.println("服务端收到了:" + data);

            //  5.查看客户端的ip和端口
            String ip = packet.getAddress().getHostAddress();
            int port = packet.getPort();
            System.out.println("对方地址: " + ip + "\n" + "对方端口: " + port);
            System.out.println("--------------------------------");
        }

        //  6.关闭套接字
        //  socket.close();
    }
}
