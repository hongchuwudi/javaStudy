package io.byteStream;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class OutTest {
    public static void main(String[] args) throws Exception {
        //  目标: 学会使用文件字节输出流
        //  1.创建 文件字节输出流的管道与目标文件接通
        //  追加不覆盖 append true
        OutputStream os1 = new FileOutputStream("file-IO\\temp.txt");       //  覆盖管道
        OutputStream os2 = new FileOutputStream("file-IO\\temp.txt", true);   // 追加
        //        os.write(97);     直接从BOF开始覆写
        //       关闭

        os1.close();
        os2.close();
        OutputStream os = new FileOutputStream("file-IO\\temp.txt", true);

        //  2.写入数据
        String s = "fdafafad";
        os.write(97);
        os.write('b');
        os.write('张');
        os.write("\r\n".getBytes());    //  兼容性换行

        //  3.写一个字节数组流出output
        byte[] bytes = "我爱你中国666".getBytes();
        os.write(bytes);
        os.write("\r\n".getBytes());    //  兼容性换行

        //  4.写一个字节数组的一部分出去
        os.write(bytes, 0, 3);
        os.write("\r\n".getBytes());    //  兼容性换行
    }
}
