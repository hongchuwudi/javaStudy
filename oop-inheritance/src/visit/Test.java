package visit;

public class Test {

    public static void main(String[] args) {
        Son son = new Son();
        son.getString();
    }
}

class Father {
    String s = "hello";
}

class Son extends Father {
    private String s = "bye";

    public void getString() {
        System.out.println(super.s);
        System.out.println(this.s);
    }
}