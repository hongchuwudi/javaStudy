package test3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Test3 {


    public static void main(String[] args) {
        //  解析字符串为单个学生信息字符串
        String info = "10001,张无忌,男,2023-07-22 11:11:12,东湖-黄鹤楼" +
                "#10002,赵敏,女,2023-07-22 09:11:21,黄鹤楼-归元禅寺" +
                "#10003,周芷若,女,2023-07-22 04:11:21,木兰文化区-东湖" +
                "#10004,小昭,女,2023-07-22 08:11:21,东湖" +
                "#10005,灭绝,女,2023-07-22 17:11:21,归元禅寺";
        String[] studentInfos = info.split("#");

        //  解析当个学生信息字符串为学生对象
        List<Student> list = getStudents(studentInfos);

        //  输出对象数组
        for (Student s : list) System.out.println(s);

        //  统计并输出最多去过的景区名称
        Map<String, Integer> lscNum = totalSoutMaxLsc(list);

        //  统计出没去过这些景点的的数量
    }

    private static Map<String, Integer> totalSoutMaxLsc(List<Student> list) {
        Map<String, Integer> lscnum = new HashMap<>();
        for (Student s : list) {
            String[] lscs = s.getLandscape().split("-");
            for (String lsc : lscs) {
                if (lscnum.containsKey(lsc)) {
                    int value = lscnum.get(lsc) + 1;
                    lscnum.remove(lsc);
                    lscnum.put(lsc, value);
                } else
                    lscnum.put(lsc, 1);
            }
        }
        //  遍历map找出频率最高的景区
        int max = 0;
        String lsc = "";
        for (Map.Entry<String, Integer> entry : lscnum.entrySet()) {
            if (max < entry.getValue()) {
                lsc = entry.getKey();
                max = entry.getValue();
            }
        }
        System.out.println(lsc + "去过的次数最多,次数为:" + max);
        return lscnum;
    }

    private static List<Student> getStudents(String[] studentInfos) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<Student> list = new ArrayList<>();
        for (String s : studentInfos) {
            String[] studentInfoArr = s.split(",");
            LocalDateTime date = LocalDateTime.parse(studentInfoArr[3], formatter);
            Long id = Long.parseLong(studentInfoArr[0]);
            list.add(new Student(id, studentInfoArr[1], studentInfoArr[2], date, studentInfoArr[4]));
        }
        return list;
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Student {
    private Long id;
    private String name;
    private String sex;
    private LocalDateTime time;
    private String landscape;

    @Override
    public String toString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy年mm月dd日 HH:mm:ss");
        return String.format(
                "学号: %d | 姓名: %s | 性别: %s | 时间: %s | 景区: %s",
                id, name, sex, time.format(timeFormatter), landscape
        );
    }
}
