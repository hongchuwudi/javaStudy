# Java `final` 关键字全面总结

`final` 是 Java 中一个重要的关键字，它可以用于修饰类、方法和变量，表示"不可改变"的含义。以下是 `final` 关键字的全面知识总结。

## 一、final 修饰变量

### 1. 基本用法

- 表示变量只能被赋值一次，之后不能修改
- 必须显式初始化（直接赋值或在构造器中赋值）

```java
final int MAX_VALUE = 100;
// MAX_VALUE = 200;  // 编译错误，不能重新赋值
```

### 2. final 变量的三种类型

#### (1) final 实例变量

- 必须在声明时、实例初始化块或构造器中初始化
- 每个对象可以有不同的值

```java
class MyClass {
    final int instanceVar;  // 必须在构造器中初始化
    
    MyClass(int value) {
        this.instanceVar = value;
    }
}
```

#### (2) final 静态变量（常量）

- 必须在声明时或静态初始化块中初始化
- 通常用大写字母命名，多个单词用下划线连接

```java
class Constants {
    public static final double PI = 3.14159;
    static final int MAX_SIZE;
    
    static {
        MAX_SIZE = 100;
    }
}
```

#### (3) final 局部变量

- 可以在声明时或后续代码中初始化（但只能赋值一次）

```java
void method() {
    final int localVar;
    localVar = 10;
    // localVar = 20;  // 编译错误
}
```

### 3. final 引用类型变量

- 引用不可变，但对象内容可以修改

```java
final List<String> list = new ArrayList<>();
list.add("Hello");  // 允许
// list = new LinkedList<>();  // 编译错误，不能重新赋值
```

## 二、final 修饰方法

### 1. 基本特性

- 表示方法不能被子类重写（override）
- 可以重载（overload）final 方法

```java
class Parent {
    final void show() { System.out.println("Parent"); }
    final void show(String msg) { }  // 重载是允许的
}

class Child extends Parent {
    // void show() { }  // 编译错误，不能重写final方法
}
```

### 2. 使用场景

1. 防止子类改变方法行为（保护关键算法）
2. 提高性能（早期Java版本，现代JVM已优化）
3. 明确设计意图（表明方法不应被修改）

## 三、final 修饰类

### 1. 基本特性

- 表示类不能被继承
- 类中所有方法隐式为 final（因为不能被继承）

```java
final class StringUtils { }
// class MyStringUtils extends StringUtils { }  // 编译错误
```

### 2. 使用场景

1. 设计不可变类（如String、Integer等）
2. 防止恶意子类化（安全考虑）
3. 表示完整、不需要扩展的类

## 四、final 与不可变对象

### 1. 不可变对象条件

1. 类声明为 final（防止子类修改行为）
2. 所有字段声明为 final
3. 不提供修改内部状态的方法
4. 对可变组件的防御性拷贝

### 2. 示例：不可变类

```java
public final class ImmutablePoint {
    private final int x;
    private final int y;
    private final List<String> labels;
    
    public ImmutablePoint(int x, int y, List<String> labels) {
        this.x = x;
        this.y = y;
        this.labels = Collections.unmodifiableList(new ArrayList<>(labels));
    }
    
    // 只提供getter方法
    public List<String> getLabels() {
        return labels;  // 返回不可修改的列表
    }
}
```

## 五、final 参数

### 1. 方法参数中的 final

- 表示参数在方法内不能被重新赋值
- 主要用于匿名内部类使用外部变量

```java
void process(final int param) {
    // param = 10;  // 编译错误
    new Thread(() -> {
        System.out.println(param);  // 匿名类中使用需要final
    }).start();
}
```

### 2. Java 8+ 的 effectively final

- 不需要显式声明 final，只要变量未被修改即可
- 在lambda表达式和匿名类中同样适用

```java
int param = 5;  // effectively final
new Thread(() -> System.out.println(param)).start();
```

## 六、final 的高级特性

### 1. 内存可见性

- final 变量的初始化保证对其他线程可见（无需同步）
- 适用于安全发布不可变对象

### 2. JVM 优化

- final 变量可能被 JVM 内联优化
- final 方法调用可能使用静态绑定（早期绑定）

### 3. 与 static 的组合

- static final 用于定义类常量
- 编译时常量会被编译器优化（直接替换为值）

```java
class Constants {
    static final int MAX = 100;  // 可能被内联
    static final String NAME = getName();  // 运行时常量
    
    private static String getName() { return "App"; }
}
```

## 七、final 的使用建议

1. **变量**：
    - 尽可能将变量声明为 final（减少错误）
    - 特别是集合和数组类型的引用

2. **方法**：
    - 设计为继承的类中慎用 final 方法
    - 工具类方法可以设为 final

3. **类**：
    - 明确不打算被继承的类声明为 final
    - 不可变类必须声明为 final

4. **性能**：
    - 不要为了性能而使用 final，现代 JVM 优化很好
    - 主要为设计意图和安全性使用

## 八、常见面试问题

1. **final、finally 和 finalize 的区别**？
    - final：修饰符，表示不可变
    - finally：异常处理块，保证执行
    - finalize：Object 的垃圾回收前调用的方法

2. **final 变量是编译时常量吗**？
    - 只有基本类型或 String 的直接赋值才可能是编译时常量
    - 其他情况是运行时常量

3. **为什么 String 类要设计为 final**？
    - 安全性：防止修改字符串内容
    - 性能：允许字符串池优化
    - 线程安全：天然不可变

4. **final 如何保证线程安全**？
    - final 变量的初始化安全发布
    - 不可变对象天然线程安全

`final` 关键字是 Java 中实现不可变性和设计安全API的重要工具，合理使用可以提高代码的健壮性和可维护性。