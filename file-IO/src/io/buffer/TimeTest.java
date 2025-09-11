package io.buffer;

import java.io.*;

public class TimeTest {
    private static final String SRC_FILE = "F:\\videos\\原神背景.mp4";
    private static final String SRC_GOAL = "F:\\videos\\原神背景_copy.mp4";
    ;

    public static void main(String[] args) {
        //  目标：缓冲流，低级流的性能分析。

        //  使用低级的字节流按照一个一个字节的形式复制文件。
        //  copyFile1();
        //  使用低级的字节流按照字节数组的形式复制文件。
//        copyFile2();
        //  使用高级的缓冲字节流按照一个一个字节的形式复制文件。
//        copyFile3();
        //  使用高级的缓冲字节流按照字节数组的形式复制文件。
        copyFile4();


    }

    public static void copyFile1() {
        long start = System.currentTimeMillis();

        try (
                InputStream fis = new FileInputStream(SRC_FILE);
                OutputStream fos = new FileOutputStream(SRC_GOAL);
        ) {
            int b;
            while ((b = fis.read()) != -1) fos.write(b);

        } catch (Exception e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("低级字节流按照一个一个字节的形式复制文件,耗费时间:" + (end - start) / 1000.0 + "s");
    }

    public static void copyFile2() {
        long start = System.currentTimeMillis();
        try (
                InputStream fis = new FileInputStream(SRC_FILE);
                OutputStream fos = new FileOutputStream(SRC_GOAL)
        ) {
            byte[] buffer = new byte[8 * 1024]; // 8KB缓冲区（最佳实践值）
            int len;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("字节数组复制耗时: " + (System.currentTimeMillis() - start) / 1000.0 + "s");
    }

    public static void copyFile3() {
        long start = System.currentTimeMillis();
        try (
                InputStream fis = new FileInputStream(SRC_FILE);
                OutputStream fos = new FileOutputStream(SRC_GOAL);
                InputStream bis = new BufferedInputStream(fis);
                OutputStream bos = new BufferedOutputStream(fos);
        ) {
            byte[] buffer = new byte[1]; // 8KB缓冲区（最佳实践值）
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("字节数组复制耗时: " + (System.currentTimeMillis() - start) / 1000.0 + "s");
    }

    public static void copyFile4() {
        long start = System.currentTimeMillis();
        try (
                InputStream fis = new FileInputStream(SRC_FILE);
                OutputStream fos = new FileOutputStream(SRC_GOAL);
                InputStream bis = new BufferedInputStream(fis);
                OutputStream bos = new BufferedOutputStream(fos);

        ) {
            byte[] buffer = new byte[8 * 1024]; // 8KB缓冲区（最佳实践值）
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("字节数组复制耗时: " + (System.currentTimeMillis() - start) / 1000.0 + "s");
    }


}
