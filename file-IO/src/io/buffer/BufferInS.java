package io.buffer;

import java.io.*;

public class BufferInS {
    public static void main(String[] args) {
        copyFile("file-IO\\temp2.txt", "file-IO\\temp3.txt");
    }

    public static void copyFile(String srcPath, String dataPath) {
        //  1.创建字节描述符 文件字符输入流管道与原文件接通
        try (
                //  try-withresource
                //  用完之后自动调用关闭方法    垃圾回收机制
                InputStream fis = new FileInputStream(srcPath);
                //  低级字节输入流包装成高级缓冲字节输入流
                InputStream bis = new BufferedInputStream(fis);     //8k缓冲池

                OutputStream fos = new FileOutputStream(dataPath);
                //  低级字节输出流包装成高级缓冲字节输出流
                OutputStream bos = new BufferedOutputStream(fos);
        ) {
            //  2.读取一个字节数组,写入一个字节数组
            byte[] buffer = new byte[1024];
            int len;

            while ((len = bis.read(buffer)) != -1)
                bos.write(buffer, 0, len);

            System.out.println("复制成功!");
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }
}
