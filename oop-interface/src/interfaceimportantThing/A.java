package interfaceimportantThing;

public interface A {
    void show1();
}

interface B {
    void show2();
}

interface C extends B, A {
    void show3();
}



