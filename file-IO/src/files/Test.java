package files;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        //  目标:创建file创建对象代表文件(文件/目录)    搞清楚提供的对文件进行操作的方法
        //  1.创建file对象, 去获取某个文件的信息

        //  绝对路径

        //  相对路径
        File f1 = new File("file-IO\\temp.txt");
        System.out.println(f1.length());

        //  创建文件 mkdirs
        File f2 = new File("file-IO\\temp2.txt");
        System.out.println(f2.mkdir());

        //  遍历

    }
}
