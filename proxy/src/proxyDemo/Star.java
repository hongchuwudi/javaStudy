package proxyDemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Star implements StarAction {
    String name;
    String song;

    @Override
    public void sing(String name) {
        System.out.println(this.name + "表演唱歌" + song);
    }

    @Override
    public String dance() {
        System.out.println(this.name + "表演跳舞 : 魅力四射");
        return "谢谢 谢谢";
    }
}
