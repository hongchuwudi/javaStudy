package TestDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

interface Callback {
    int forEachNum();
}

public class Test {
    public static void main(String[] args) throws Exception{
        TestV(new FUnc(100));
        TestV(new FUnc(200));
        TestV(new FUnc(10000));
        TestV(new FUnc(100000));
    }

    public static void TestV(FUnc func) throws InterruptedException {
        long curTime = System.currentTimeMillis();

        //  测试函数的地方
        System.out.print("1-" + func.getN() + "===" + func.forEachNum());
        Thread.sleep(1000);
        long endTime = System.currentTimeMillis();
        System.out.println("    总耗时:" + ((endTime - curTime) / 1000) + "s");

    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class FUnc implements Callback {
    private int n;

    public int forEachNum() {
        Integer result = 0;

        for (int i = 0; i < n; ++i)
            result += i;

        return result;
    }
}
