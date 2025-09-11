# Java 函数式接口与 Lambda 表达式知识总结

## 一、函数式接口(Functional Interface)

### 1. 基本概念
- **定义**：只有一个抽象方法的接口（可以有多个默认方法或静态方法）
- **注解**：`@FunctionalInterface`（非必须，但编译器会检查是否符合函数式接口规范）
- **作用**：为Lambda表达式和方法引用提供目标类型

### 2. 常见内置函数式接口

#### (1) 核心接口（java.util.function包）

| 接口                | 方法               | 描述                     | 示例                     |
|---------------------|--------------------|--------------------------|--------------------------|
| `Supplier<T>`       | `T get()`          | 无参返回结果             | () -> "Hello"            |
| `Consumer<T>`       | `void accept(T t)` | 接收参数无返回值         | s -> System.out.println(s)|
| `Function<T,R>`     | `R apply(T t)`     | 接收T类型返回R类型       | s -> s.length()          |
| `Predicate<T>`      | `boolean test(T t)`| 条件判断                 | s -> s.isEmpty()         |
| `UnaryOperator<T>`  | `T apply(T t)`     | 一元运算（同类型转换）   | s -> s.toUpperCase()     |
| `BinaryOperator<T>` | `T apply(T t1, T t2)` | 二元运算（同类型操作） | (a,b) -> a + b           |

#### (2) 基本类型特化接口
- `IntConsumer`, `LongFunction`, `DoublePredicate`等
- 避免自动装箱拆箱开销

### 3. 自定义函数式接口
```java
@FunctionalInterface
interface StringProcessor {
    String process(String input);
    
    default void printInfo() {
        System.out.println("This is a string processor");
    }
}
```

## 二、Lambda表达式

### 1. 基本语法
```java
(parameters) -> expression
或
(parameters) -> { statements; }
```

### 2. 特征
- **匿名**：没有显式名称
- **函数式**：属于函数式接口的实例
- **简洁**：比匿名内部类更简洁
- **类型推断**：编译器可推断参数类型

### 3. 使用示例

#### (1) 无参形式
```java
Runnable r = () -> System.out.println("Hello Lambda");
```

#### (2) 单参数（可省略括号）
```java
Consumer<String> c = s -> System.out.println(s);
```

#### (3) 多参数
```java
Comparator<Integer> comp = (a, b) -> a.compareTo(b);
```

#### (4) 代码块形式
```java
Function<String, Integer> f = s -> {
    System.out.println("Processing: " + s);
    return s.length();
};
```

### 4. 变量捕获
- 可以访问**final或effectively final**的局部变量
- 可以访问实例变量和静态变量（无限制）
```java
int offset = 10; // effectively final
Function<Integer, Integer> f = x -> x + offset;
```

## 三、方法引用(Method Reference)

### 1. 四种形式
| 类型                   | 语法                  | 示例                     |
|------------------------|-----------------------|--------------------------|
| 静态方法引用           | `ClassName::staticMethod` | `Math::sqrt`         |
| 实例方法引用（特定对象）| `instance::method`    | `System.out::println`|
| 实例方法引用（任意对象）| `ClassName::method`   | `String::length`      |
| 构造方法引用           | `ClassName::new`      | `ArrayList::new`      |

### 2. 使用场景
```java
// 替代Lambda表达式
Consumer<String> c1 = s -> System.out.println(s);
Consumer<String> c2 = System.out::println;

// 排序示例
List<String> names = Arrays.asList("Bob", "Alice", "Charlie");
names.sort(String::compareToIgnoreCase);
```

## 四、Lambda与匿名内部类比较

| 特性                | Lambda表达式         | 匿名内部类             |
|---------------------|---------------------|-----------------------|
| 语法                | 简洁                | 冗长                  |
| this含义            | 指向外部类          | 指向自身              |
| 编译方式            | 动态invokedynamic   | 生成.class文件        |
| 变量访问            | 仅final/effectively final | 无限制       |
| 目标类型            | 必须是函数式接口    | 可以是接口或抽象类    |

## 五、实际应用场景

### 1. 集合操作
```java
List<String> list = Arrays.asList("a", "b", "c");
list.forEach(System.out::println);

list.removeIf(s -> s.length() == 0);
```

### 2. 线程创建
```java
new Thread(() -> System.out.println("Running")).start();
```

### 3. 流式处理(Stream API)
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
int sum = numbers.stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(n -> n * 2)
                .sum();
```

### 4. 事件处理
```java
button.addActionListener(e -> System.out.println("Button clicked"));
```

## 六、注意事项

1. **变量捕获限制**：只能捕获final或effectively final的局部变量
2. **性能考虑**：
    - Lambda首次调用会有初始化开销
    - 简单操作比匿名内部类更高效
3. **调试困难**：堆栈跟踪不如匿名内部类清晰
4. **重载问题**：参数类型推断可能不明确
   ```java
   interface Adder { int add(int a, int b); }
   interface SmartAdder { int add(double a, double b); }
   
   // 以下会编译错误，需要显式类型转换
   // Adder adder = (a, b) -> a + b;
   ```

函数式接口和Lambda表达式是Java 8最重要的特性之一，它们使Java能够更简洁地实现函数式编程范式，大大提高了代码的可读性和编写效率。