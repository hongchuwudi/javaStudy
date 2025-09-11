package io.buffer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

public class BufferWrite {
    public static void main(String[] args) {
        //  目标:读取数据到
        try (
                //  1.创建一个字符输出流对象,并制定写出的目的地
                Writer fw = new FileWriter("file-IO\\temp1.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
        ) {
            //  2.写一个字符出去,  public void write(int c)
            fw.write('a');
            fw.write('b');
            fw.write(98);
            fw.write("涨jiashi`1111");
            bw.newLine();

            //  写一个字符串出去, public void wirte(char[] cbuf)
            char[] chars = "java".toCharArray();
            fw.write(chars);
            fw.write(chars, 0, 2);
            bw.newLine();
            //  刷新缓冲区,cache缓存清空
            fw.flush();
            //  关闭管道流
            //  fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

