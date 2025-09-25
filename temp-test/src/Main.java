public class Main {
    public static void main(String[] args) {
        int a = 1, b = 2;

        // 输出n=1和n=2
        System.out.print("1,2");

        // 输出n=3到45
        for (int n = 3; n <= 45; n++) {
            int current = a + b;
            System.out.print("," + current);
            a = b;
            b = current;
        }
    }
}