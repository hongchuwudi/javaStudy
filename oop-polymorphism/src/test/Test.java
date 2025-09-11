package test;

public class Test {
    public static void main(String[] args) {
        Father son = new Son2();
        son.sayH();
    }
}

class Father {
    public void sayH() {
        System.out.println("helloFather");
    }

    ;
}

class Son1 extends Father {
    public void sayH() {
        System.out.println("helloSon1");
    }

    ;
}

class Son2 extends Father {
    public void sayH() {
        System.out.println("helloSon2");
    }

    ;
}
