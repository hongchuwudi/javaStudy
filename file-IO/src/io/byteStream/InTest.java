package io.byteStream;

import java.io.FileInputStream;
import java.io.InputStream;

public class InTest {
    public static void main(String[] args) throws Exception {
        //  目标:掌握文件字节输入流读取文件中的字节数组到内存中来
        //  1.创建文件字节输入流管道与源文件接通

        InputStream inputStream = new FileInputStream("file-IO\\temp.txt");
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            String str = new String(buffer, 0, len);
            System.out.print(str);
        }
    }
}
