package operationClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String name;
    private int age;

    public void eat() {
        System.out.println("零个变量");
    }

    public void eat(int times) {
        System.out.println("int变量");
    }

    private void eat(String string) {
        System.out.println("String变量");
    }
}
