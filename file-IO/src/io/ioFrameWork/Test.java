package io.ioFrameWork;

import org.apache.commons.io.FileUtils;

import java.io.File;

public class Test {
    public static void main(String[] args) throws Exception {
        //  目标:io框架
        FileUtils.copyFile(new File("file-IO/temp3.txt"), new File("file-IO/temp2.txt"));
    }
}
