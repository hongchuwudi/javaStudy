package interfaceTest;

public class Test {
    public static void main(String[] args) {
        People p = new Student();
        People d = new Student();
        BoyFriend bf = new Student();
        //  接口可以实现面向接口编程 更有利于解耦合,
    }
}

interface Driver {
}

interface BoyFriend {
}

class People {
}

class Student extends People implements Driver, BoyFriend {
}

class Teacher implements Driver, BoyFriend {
}
