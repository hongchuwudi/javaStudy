public class StringUtil {
    public static void printNumber(String name) {
        if (name == null) {
            System.out.println("参数为null");
            return;
        }
        System.out.println("名字长度是:" + name.length());
    }

    public static int getMaxIndex(String data) {
        if (data == null || "".equals(data)) {
            return -1;
        }
        return data.length() - 1;
    }
}