package searchFiles;

import java.io.File;

public class Main { // 类名首字母建议大写
    public static void main(String[] args) {
        File dir = new File("D://01_program"); // 修正路径大小写
        System.out.println("搜索目录: " + dir.getAbsolutePath());
        searchFile(dir, "包装类.md");
    }

    public static void searchFile(File dir, String fileName) {
        // 1. 校验目录合法性
        if (dir == null || !dir.exists() || dir.isFile()) {
            return;
        }

        // 2. 获取子文件列表
        File[] files = dir.listFiles();
        if (files == null) {
            System.err.println("无法访问: " + dir.getAbsolutePath());
            return;
        }

        // 3. 遍历子文件
        for (File file : files) {
            if (file.isFile()) {
                // 4. 精确匹配文件名
                if (file.getName().equals(fileName)) {
                    System.out.println("找到目标文件: " + file.getAbsolutePath());
                }
            } else {
                // 5. 递归搜索子目录
                searchFile(file, fileName);
            }
        }
    }
}