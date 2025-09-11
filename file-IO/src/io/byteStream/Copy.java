package io.byteStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Copy {
    public static void main(String[] args) throws Exception {
        copyFile("file-IO\\temp.txt", "file-IO\\temp1.txt");
    }

    public static void copyFile(String srcPath, String dataPath) {
        //  1.创建字节描述符 文件字符输入流管道与原文件接通
        try (
                //  try-withresource
                //  用完之后自动调用关闭方法    垃圾回收机制
                InputStream fis = new FileInputStream(srcPath);
                OutputStream fos = new FileOutputStream(dataPath);
        ) {
            //  2.读取一个字节数组,写入一个字节数组
            byte[] buffer = new byte[1024];
            int len;

            while ((len = fis.read(buffer)) != -1)
                fos.write(buffer, 0, len);

            System.out.println("复制成功!");
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }
}
