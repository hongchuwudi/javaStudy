package io.printStream;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintStreamTest {
    public static void main(String[] args) {
        try (
                PrintStream ps = new PrintStream(new FileOutputStream("file-IO/temp2.txt", true));
        ) {
            ps.println(91);
            ps.println(97);
            ps.println("97");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
