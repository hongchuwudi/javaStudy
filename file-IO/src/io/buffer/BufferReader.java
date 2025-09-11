package io.buffer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

public class BufferReader {
    public static void main(String[] args) {
        //  目标:搞清楚缓冲输入流读取字符内容到程序中来
        try (
                //  1.创建文件字符输入流与源文件接通
                Reader fr = new FileReader("file-IO\\temp.txt");
                BufferedReader br = new BufferedReader(fr);
        ) {
//                //  2.定义一个字符数组,每次读取多个字符
//            char[] chs = new char[3];
//            int len;    //  用于记录每次接了多少个字符
//            while ((len = br.read(chs)) != -1){
//                //  3.每次读取多个字符,并把字符数组转换成字符串输出
//                String str = new String(chs,0,len);
//                System.out.println(str);
//            }
            String line;
            while ((line = br.readLine()) != null)
                System.out.println(line);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
