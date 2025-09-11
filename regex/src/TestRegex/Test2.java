package TestRegex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test2 {
    public static void main(String[] args) throws Exception {
        final String SRC_1 = "D:\\01_program\\05_java\\"
                + "intellij idea projects\\"
                + "javaStudy1\\regex\\src\\ipv4.txt";
        String str1 = readFileToString(SRC_1);
        System.out.println(str1);


        //  目标:匹配所有的四位数字
        //  1.创建一个String regex( \\d表示一个任意的数字 )
        String regex1 = "\\d\\d\\d\\d";
        //  2.创建匹配模式对象
        Pattern pattern1 = Pattern.compile(regex1);
        //  3.创建一个匹配器 按照正则
        Matcher matcher1 = pattern1.matcher(str1);

        //  4.开始匹配
        while(matcher1.find()){
            System.out.println("找到: " + matcher1.group(0));
        }
    }

    public static String readFileToString(final String SRC) {
        String result = "";
        try (
                Reader fr = new FileReader(SRC);
                BufferedReader br = new BufferedReader(fr);
        ) {
            String line;
            while ((line = br.readLine()) != null)
                result += line + "\n";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
