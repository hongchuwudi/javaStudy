package io.other;

import java.io.*;

public class Test1 {
    public static void main(String[] args) {
        //  目标: 不同编码下 读取乱码的情况
        //  获取原始字节再根据不用的字符集编码转成字符集
        try (
                //  先提取文件的原始字节流
                InputStream is = new FileInputStream("file-IO/temp2.txt");
                Reader isr = new InputStreamReader(is, "GBk");  //  字符集编码不区分大小写
                BufferedReader br = new BufferedReader(isr);
        ) {
            String line;
            while ((line = br.readLine()) != null) System.out.println(line);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
