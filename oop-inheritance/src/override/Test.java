package override;

//  方法重写的其它注意事项
//  子类重写父类方法时，访问权限必须大于或者等于父类该方法的权限（public>protected>缺省）。
//  重写的方法返回值类型，必须与被重写方法的返回值类型一致，或者范围更小。
//  私有方法、静态方法不能被重写，如果重写会报错的。
public class Test {
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.cry();
    }
}

class Cat extends Animal {
    //  方法重写:子类中重写父类的未实现的方法,方法名称名称一样,加上override的注解
    @Override
    public void cry() {
        System.out.println("mmm");
    }
}

class Animal {
    public void cry() {
        System.out.println("what");
    }
}
