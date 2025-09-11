package io.datastream;

import java.io.DataOutputStream;
import java.io.FileOutputStream;

public class DataStreamTest {
    public static void main(String[] args) {
        try (
                DataOutputStream dos = new DataOutputStream(new FileOutputStream("file-IO/temp3.txt"));
        ) {
            dos.writeByte(34);
            dos.writeUTF("你好");
            dos.writeInt(11);
            dos.writeDouble(11.4);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
