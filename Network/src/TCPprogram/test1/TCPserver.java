package TCPprogram.test1;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPserver {
    public static void main(String[] args) throws Exception {
        //  目标:实现了TCP通信下多发多收 实现服务端

        //  1.创建服务端serverSocket对象 绑定端口号 监听客户端链接
        ServerSocket ss = new ServerSocket(9999);

        //  2.调用accept方法 阻塞等待客户端连接,一但有客户端连接会返回一个socket对象
        Socket socket = ss.accept();

        //  3.获取输入流,读取客户端发送的数据
        InputStream is = socket.getInputStream();

        //  4.读取数据(怎么发怎么收)
        DataInputStream dis = new DataInputStream(is);

        while (true) {
            //  5.读取数据
            int id = dis.readInt();
            String msg = dis.readUTF();

            String ip = socket.getInetAddress().getHostAddress();
            Integer port = socket.getPort();
            System.out.println("客户端ID:" + id + "\n" + "客户端消息" + msg);
            System.out.println("客户端地址:" + ip + "\n" + "客户端端口" + port);
            System.out.println("--------------------------------------");
        }

    }
}
