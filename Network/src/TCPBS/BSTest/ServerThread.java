package TCPBS.BSTest;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class ServerThread extends Thread {
    private final Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String htmlContent = """
                <html>
                    <head>
                        <meta charset='utf-8'>
                        <title>黑马Java磊哥的视频</title>
                    </head>
                    <body>
                        <h1 style='color:red;font-size:20px'>听黑马Java磊哥的视频</h1>
                    </body>
                </html>
                """;

        try (Socket socket = this.socket;
             OutputStream os = socket.getOutputStream();
             PrintStream ps = new PrintStream(os, true, "UTF-8")) {

            byte[] htmlBytes = htmlContent.getBytes("UTF-8");

            // 写入 HTTP 响应头
            ps.println("HTTP/1.1 200 OK");
            ps.println("Content-Type: text/html; charset=utf-8");
            ps.println("Content-Length: " + htmlBytes.length);
            ps.println();  // 空行分隔头部和内容
            ps.print(htmlContent);  // 写入 HTML 内容

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}