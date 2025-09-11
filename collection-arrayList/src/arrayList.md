# Java ArrayList 知识全面总结

## 一、ArrayList 基础特性

### 1. 核心特点

- **动态数组**：基于数组实现的动态大小列表
- **非线程安全**：不同步，多线程环境下需要外部同步
- **快速随机访问**：实现了`RandomAccess`接口，通过索引访问快(O(1))
- **有序可重复**：保持插入顺序，允许重复元素
- **允许null值**：可以存储null元素

### 2. 类继承关系

```java
java.lang.Object
  ↳ java.util.AbstractCollection<E>
     ↳ java.util.AbstractList<E>
        ↳ java.util.ArrayList<E>
```

### 3. 重要接口实现

- `List`：基础列表操作
- `RandomAccess`：标记接口，支持快速随机访问
- `Cloneable`：可克隆
- `Serializable`：可序列化

## 二、ArrayList 内部实现

### 1. 核心字段(JDK 8)

```java
transient Object[] elementData; // 实际存储元素的数组
private int size;              // 当前元素数量
private static final int DEFAULT_CAPACITY = 10; // 默认初始容量
```

### 2. 扩容机制

- **初始容量**：无参构造默认10，可指定初始容量
- **扩容公式**：`newCapacity = oldCapacity + (oldCapacity >> 1)` (约1.5倍)
- **扩容过程**：
    1. 检查是否需要扩容
    2. 计算新容量
    3. `Arrays.copyOf`创建新数组
    4. 数据复制到新数组

### 3. 重要构造方法

```java
ArrayList()                  // 默认容量10
ArrayList(int initialCapacity) // 指定初始容量
ArrayList(Collection<? extends E> c) // 用已有集合初始化
```

## 三、ArrayList 核心操作

### 1. 添加元素

```java
boolean add(E e)                     // 尾部添加
void add(int index, E element)       // 指定位置插入
boolean addAll(Collection<? extends E> c) // 添加集合
```

### 2. 删除元素

```java
E remove(int index)                  // 删除指定位置元素
boolean remove(Object o)             // 删除首次出现的指定元素
void clear()                         // 清空列表
```

### 3. 查询与访问

```java
E get(int index)                     // 获取指定位置元素
int indexOf(Object o)                // 查找元素首次出现位置
int lastIndexOf(Object o)            // 查找元素最后出现位置
boolean contains(Object o)           // 是否包含元素
```

### 4. 修改操作

```java
E set(int index, E element)          // 替换指定位置元素
```

### 5. 其他操作

```java
int size()                           // 元素数量
boolean isEmpty()                    // 是否为空
Object[] toArray()                   // 转为数组
<T> T[] toArray(T[] a)               // 转为指定类型数组
```

## 四、ArrayList 性能分析

| 操作                  | 时间复杂度   | 说明         |
|---------------------|---------|------------|
| add(E e)            | O(1) 均摊 | 尾部添加       |
| add(int index, E e) | O(n)    | 中间插入需要移动元素 |
| remove(int index)   | O(n)    | 需要移动元素     |
| remove(Object o)    | O(n)    | 需要遍历查找     |
| get(int index)      | O(1)    | 直接数组访问     |
| set(int index, E e) | O(1)    | 直接数组访问     |
| contains(Object o)  | O(n)    | 需要遍历查找     |

## 五、ArrayList 与 Vector 比较

| 特性   | ArrayList | Vector             |
|------|-----------|--------------------|
| 线程安全 | 非线程安全     | 线程安全(synchronized) |
| 扩容增量 | 1.5倍      | 可指定(默认2倍)          |
| 性能   | 更高        | 较低                 |
| 迭代器  | fail-fast | fail-fast          |
| 使用场景 | 单线程环境     | 多线程环境(已不推荐)        |

## 六、ArrayList 优化技巧

### 1. 初始化指定容量

```java
// 预估有1000个元素
List<String> list = new ArrayList<>(1000);
```

- **优势**：避免多次扩容和数据复制

### 2. 批量操作优化

```java
// 批量添加
list.addAll(otherList);
// 批量删除(JDK8+)
list.removeIf(e -> e.startsWith("test"));
```

### 3. 遍历方式选择

```java
// 1. for循环(随机访问快)
for(int i=0; i<list.size(); i++) {
    String s = list.get(i);
}

// 2. 迭代器(通用)
for(Iterator<String> it = list.iterator(); it.hasNext();) {
    String s = it.next();
}

// 3. foreach(语法糖，实际使用迭代器)
for(String s : list) {
    // ...
}

// 4. JDK8+ forEach
list.forEach(s -> System.out.println(s));
```

## 七、ArrayList 线程安全方案

### 1. 使用Collections工具类

```java
List<String> syncList = Collections.synchronizedList(new ArrayList<>());
```

### 2. 使用CopyOnWriteArrayList

```java
List<String> cowList = new CopyOnWriteArrayList<>();
```

### 3. 外部同步控制

```java
List<String> list = new ArrayList<>();
// 写操作同步
synchronized(list) {
    list.add("item");
}
```

## 八、ArrayList 特殊用法

### 1. 子列表视图

```java
List<String> subList = list.subList(1, 3);
// 注意：子列表修改会影响原列表
```

### 2. 转换为不可变列表(JDK9+)

```java
List<String> immutable = List.copyOf(list);
```

### 3. 并行流处理

```java
list.parallelStream().forEach(System.out::println);
```

## 九、常见问题与注意事项

1. **并发修改异常**：
   ```java
   // 错误示例
   for(String s : list) {
       if(s.equals("remove")) {
           list.remove(s); // 抛出ConcurrentModificationException
       }
   }
   
   // 正确做法
   Iterator<String> it = list.iterator();
   while(it.hasNext()) {
       if(it.next().equals("remove")) {
           it.remove(); // 使用迭代器的remove方法
       }
   }
   ```

2. **内存浪费**：
    - 大量删除后调用`trimToSize()`释放空间
   ```java
   list.trimToSize(); // 将容量调整为当前元素数量
   ```

3. **与Arrays.asList()区别**：
    - `Arrays.asList()`返回的是固定大小的列表
    - 不支持add/remove操作

4. **浅拷贝问题**：
    - `clone()`和`new ArrayList<>(oldList)`都是浅拷贝
    - 深拷贝需要元素也实现`Cloneable`或手动复制

## 十、JDK版本变化

### JDK 7 → JDK 8

- 新增`removeIf()`、`replaceAll()`、`sort()`等方法
- 新增`Spliterator`支持并行处理

### JDK 8 → JDK 9

- 新增`List.of()`工厂方法创建不可变列表
- 优化内部数组存储

### JDK 11 → JDK 17

- 进一步优化内存使用
- 增强并行处理能力

ArrayList作为Java集合框架中最常用的类之一，理解其实现原理和特性对于编写高效Java程序至关重要。合理使用ArrayList可以显著提升程序性能，特别是在需要频繁随机访问的场景中。