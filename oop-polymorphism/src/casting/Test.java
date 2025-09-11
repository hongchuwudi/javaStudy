package casting;

public class Test {
    public static void main(String[] args) {

    }
}

class animal {
    public animal(String s) {
        //  动物类型
        this.name = s;
    }

    public String name;
}

class guigui extends animal {
    public guigui(String name) {
        super(name);
    }

    public void pa() {
        System.out.println("papapa");
    }
}

class wolf extends animal {
    public wolf(String name) {
        super(name);
    }

    public void zou() {
        System.out.println("zouzouzou");
    }
}
