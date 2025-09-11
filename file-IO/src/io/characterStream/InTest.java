package io.characterStream;

import java.io.FileReader;
import java.io.Reader;

public class InTest {
    public static void main(String[] args) throws Exception {
        //  目标:掌握文件字符输入流读取字符内容到程序中去
        //  1.创建一个文件字符输入流和源文件接通
        try (
                Reader fr = new FileReader("file-IO\\temp.txt");
        ) {
            //  2.定义一个字符数组用于表示每次读多少个字符
            char[] chs = new char[1024 * 8];
            int len;
            while ((len = fr.read(chs)) != -1) {
                String s = new String(chs, 0, len);
                System.out.print(chs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
