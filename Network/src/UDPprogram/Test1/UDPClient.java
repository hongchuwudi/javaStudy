package UDPprogram.Test1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        System.out.println("===UDPclient===");
        //  目标:完成udp通信一发一收:客户端开发

        //  1.创建发送端对象
        DatagramSocket socket = new DatagramSocket();


        Scanner sc = new Scanner(System.in);
        while (true) {
            //  2.创建数据包对象
        /*
         public DatagramPacket(byte[] buf, int length,InetAddress address, int port)
         *参数一：发送的数据，字节数组（韭菜）
         *参数二：发送的字节长度。
         *参数三：目的地的IP地址。
         *参数四：服务端程序的端口号
         */
            System.out.print(">>>");
            String msg = sc.nextLine();

            byte[] bytes = msg.getBytes();
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 8080);    //  数据包,数据长度,目的地址

            //  3.让发送端对象发数据包的数据
            socket.send(packet);

            //  4.关闭套接字
            //  socket.close();
        }
    }
}
