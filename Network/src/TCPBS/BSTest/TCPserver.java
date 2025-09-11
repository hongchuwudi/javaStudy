package TCPBS.BSTest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPserver {
    public static void main(String[] args) {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 8080;
        ExecutorService threadPool = Executors.newFixedThreadPool(100); // 限制最大线程数

        try (ServerSocket ss = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            // 添加 JVM 关闭钩子，确保 ServerSocket 关闭
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    ss.close();
                    threadPool.shutdown();
                    System.out.println("Server stopped.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));

            while (true) {
                try {
                    Socket socket = ss.accept();
                    threadPool.execute(new ServerThread(socket)); // 使用线程池
                } catch (IOException e) {
                    System.err.println("Client connection failed: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("ServerSocket failed: " + e.getMessage());
        }
    }
}