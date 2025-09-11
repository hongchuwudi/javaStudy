package constructor;

public class Test {
    public static void main(String[] args) {
        Father father = new Father(0);
        Son son = new Son(22, 11);
        System.out.println(son.num1);
        System.out.println(son.num2);
        System.out.println(father.num1);
    }

}

class Father {
    public int num1;

    public Father(int num1) {  // 注意：没有返回类型，类名完全匹配
        this.num1 = num1;  // 使用this区分成员变量和参数
    }
}

class Son extends Father {
    public int num2;

    public Son(int num2, int num1) {
        super(num1);
        this.num2 = num2;
    }
}

class bro extends Father {
    public int num2;

    public bro(int num2, int num1) {
        super(num1);
        this.num2 = num2;
    }

    public bro(int num1) {
        this(num1, num1);
        //  兄弟构造器
    }
}