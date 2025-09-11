package anonymousInternalClass.intances3;

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
}
