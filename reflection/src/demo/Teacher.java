package demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private String name;
    private int age;
    private String hobby;
    private Double salary;
    private String className;
    private char sex;
    private String phone;
}
