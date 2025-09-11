package io.buffer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class BufferSort {
    public static void main(String[] args) {
        // 打印当前工作目录（调试用）
        System.out.println("当前工作目录: " + System.getProperty("user.dir"));

        try (
                BufferedReader br = new BufferedReader(new FileReader("file-IO/temp2.txt"));
                BufferedWriter bw = new BufferedWriter(new FileWriter("file-IO/temp2_sorted.txt")); // 避免覆盖原文件
        ) {
            List<String> data = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line);
            }

            data.sort(null); // 自然排序

            for (String s : data) {
                bw.write(s);
                bw.newLine();
            }
            System.out.println("文件排序完成！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}