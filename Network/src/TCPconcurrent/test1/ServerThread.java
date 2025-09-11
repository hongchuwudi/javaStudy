package TCPconcurrent.test1;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("客户端" + socket.getInetAddress() + "建立了链接");
            //  读取管道的信息
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
        } catch (IOException e) {
            System.out.println("客户端" + "已失去链接");
        }
    }
}
