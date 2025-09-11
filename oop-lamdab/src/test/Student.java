package test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    //  姓名 性别 身高 年龄
    private String name;
    private int age;
    private double height;
    private char sex;

    public static int compareByAge(Student o1, Student o2) {
        return o1.getAge() - o2.getAge();
    }


}
