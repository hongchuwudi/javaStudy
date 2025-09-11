public class Test {
    public static void main(String[] args) {
        // 创建一个字符串
        String str = "hello world";
        String str1 = new String("hello world");

        // 截取字符串
        String substring = str.substring(1, 2);
        System.out.println(substring);

        // + 拼接字符串
        String s = str + " " + str1;
        System.out.println(s);

        // 字符串的比较
        System.out.println(str.equals(str1));
        System.out.println(str == str1);

        // 字符串常用方法:
        // 1.charAt 返回指定索引处的 char 值
        char c = str.charAt(0);
        System.out.println("charAt(0):" + c);

        // 2.codePointAt 返回指定索引处的字符（Unicode 代码点）
        int codePoint = str.codePointAt(0);
        System.out.println("codePointAt(0):" + codePoint);

        // 3.offsetByCodePoints 确定由索引位置（索引位置）开始的字符序列的偏移量
        int offset = str.offsetByCodePoints(1, 1);
        System.out.println("offsetByCodePoints(1, 1):" + offset);

        // 4.compareTo 返回此字符串与指定字符串的顺序
        int compareTo = str.compareTo(str1);
        System.out.println("compareTo(str1):" + compareTo);

        //
    }
}
