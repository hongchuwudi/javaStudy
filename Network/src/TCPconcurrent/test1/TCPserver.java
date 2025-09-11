package TCPconcurrent.test1;

import java.net.ServerSocket;
import java.net.Socket;

public class TCPserver {
    public static void main(String[] args) throws Exception {
        //  目标:实现了TCP通信下多发多收 实现服务端

        //  1.创建服务端serverSocket对象 绑定端口号 监听客户端链接
        ServerSocket ss = new ServerSocket(9999);

        while (true) {

            //  2.调用accept方法 阻塞等待客户端连接,一但有客户端连接会返回一个socket对象
            Socket socket = ss.accept();

            //  3.把这个客户端管道交给一个线程 负责这个管道的消息
            new ServerThread(socket).start();
        }
    }
}

