package TCPprogram.test1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TCPclient {
    public static void main(String[] args) throws IOException {
        System.out.println("===客户端建立===");
        //  目标:实现了TCP通信下多发多收 实现客户端

        //  1.常见socket管道对象 请求与服务端socket链接 可靠连接
        Socket socket = new Socket("127.0.0.1", 9999);

        //  2.从 socket 通信管道中 获得一个字节输出流
        OutputStream os = socket.getOutputStream();

        //  3.特殊数据流
        DataOutputStream dos = new DataOutputStream(os);
        Scanner sc = new Scanner(System.in);
        int idx = 0;

        while (true) {
            System.out.print(">>>");

            dos.writeInt(idx++);
            String msg = sc.nextLine();

            if ("eixt".equals(msg)) {
                System.out.println("客户端退出成功!");

                dos.close();        //  关闭输出流
                socket.close();     //  关闭socket
            }
            dos.writeUTF(msg);
            dos.flush();
        }

        //  4.关闭
        //  socket.close();
    }
}
