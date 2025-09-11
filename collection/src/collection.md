### Java集合框架（Collection Framework）知识总结

---

#### **1. 集合框架的核心接口与层级结构**

Java集合框架由**接口**、**实现类**和**算法工具类**组成，核心接口如下：

| 接口               | 描述                                                      |
|------------------|---------------------------------------------------------|
| **`Collection`** | 所有集合的根接口（除`Map`外），定义基本操作（添加、删除、遍历等）                     |
| **`List`**       | 有序、可重复的集合，支持索引访问（如`ArrayList`, `LinkedList`）            |
| **`Set`**        | 无序、不可重复的集合（如`HashSet`, `TreeSet`）                       |
| **`Queue`**      | 队列结构，支持先进先出（FIFO）或优先级操作（如`LinkedList`, `PriorityQueue`） |
| **`Deque`**      | 双端队列，支持两端操作（如`ArrayDeque`）                              |
| **`Map`**        | 键值对集合（如`HashMap`, `TreeMap`）                            |

---

#### **2. 核心实现类及特性**

##### **List接口的实现类**

| 类                          | 数据结构 | 线程安全 | 特点                             |
|----------------------------|------|------|--------------------------------|
| **`ArrayList`**            | 动态数组 | 否    | 随机访问快（O(1)），增删中间元素慢（需要移动元素）    |
| **`LinkedList`**           | 双向链表 | 否    | 增删快（O(1)），随机访问慢（O(n)），支持队列和栈操作 |
| **`Vector`**               | 动态数组 | 是    | 过时，使用`synchronized`实现线程安全，性能差  |
| **`CopyOnWriteArrayList`** | 动态数组 | 是    | 写操作时复制新数组，适合读多写少场景             |

---

##### **Set接口的实现类**

| 类                         | 数据结构             | 特点                              |
|---------------------------|------------------|---------------------------------|
| **`HashSet`**             | 哈希表（基于`HashMap`） | 无序、快速查询（O(1)），允许`null`值         |
| **`LinkedHashSet`**       | 哈希表+双向链表         | 保留插入顺序，迭代效率高                    |
| **`TreeSet`**             | 红黑树              | 自然排序或自定义排序，查询/增删O(log n)，支持范围查询 |
| **`CopyOnWriteArraySet`** | 动态数组             | 线程安全，基于`CopyOnWriteArrayList`   |

---

##### **Queue/Deque接口的实现类**

| 类                         | 数据结构     | 特点                        |
|---------------------------|----------|---------------------------|
| **`PriorityQueue`**       | 堆（优先级队列） | 元素按自然顺序或自定义顺序排序           |
| **`ArrayDeque`**          | 动态数组     | 双端队列，比`LinkedList`更高效     |
| **`LinkedBlockingQueue`** | 链表       | 线程安全，支持阻塞操作（常用于生产者-消费者模型） |

---

##### **Map接口的实现类**

| 类                       | 数据结构                 | 线程安全 | 特点                                    |
|-------------------------|----------------------|------|---------------------------------------|
| **`HashMap`**           | 数组+链表/红黑树（JDK8+）     | 否    | 快速查询（O(1)），允许`null`键和值                |
| **`LinkedHashMap`**     | 哈希表+双向链表             | 否    | 保留插入顺序或访问顺序（LRU缓存实现）                  |
| **`TreeMap`**           | 红黑树                  | 否    | 键按自然顺序或自定义顺序排序，查询/增删O(log n)          |
| **`Hashtable`**         | 哈希表                  | 是    | 过时，使用`synchronized`实现线程安全，不允许`null`键值 |
| **`ConcurrentHashMap`** | 分段锁（JDK7）或CAS（JDK8+） | 是    | 高并发场景下的线程安全Map，性能优于`Hashtable`        |

---

#### **3. 工具类与辅助API**

1. **`Collections`工具类**
    - 提供静态方法：排序（`sort`）、同步化（`synchronizedList`）、不可变集合（`unmodifiableList`）等。
    - 示例：`Collections.synchronizedList(new ArrayList<>())`。

2. **`Arrays`工具类**
    - 处理数组与集合转换：`Arrays.asList(T...)`（返回固定大小的列表）。

3. **迭代器（`Iterator`与`ListIterator`）**
    - `Iterator`：遍历集合，支持`hasNext()`、`next()`、`remove()`。
    - `ListIterator`：双向遍历，支持修改元素（`set()`、`add()`）。

4. **比较器（`Comparator`与`Comparable`）**
    - `Comparable`：自然排序（实现`compareTo()`方法）。
    - `Comparator`：自定义排序（实现`compare()`方法）。

5. **Java 8+ Stream API**
    - 对集合进行函数式操作（过滤、映射、归约等）：
      ```java
      list.stream().filter(x -> x > 0).map(String::valueOf).collect(Collectors.toList());
      ```

---

#### **4. 核心操作与示例**

##### **List操作示例**

```java
List<String> list = new ArrayList<>();
list.add("A");
list.add(0, "B"); // 插入到索引0
String element = list.get(0); // 获取元素
list.remove("B"); // 删除元素
```

##### **Map操作示例**

```java
Map<String, Integer> map = new HashMap<>();
map.put("Key1", 1);
map.putIfAbsent("Key1", 2); // 仅当Key不存在时插入
int value = map.getOrDefault("Key2", 0); // 不存在则返回默认值
map.forEach((k, v) -> System.out.println(k + ": " + v)); // 遍历
```

---

#### **5. 线程安全与并发集合**

- **非线程安全类**：`ArrayList`, `HashMap`, `HashSet`等。
- **线程安全替代方案**：
    - **同步包装类**：`Collections.synchronizedXXX()`。
    - **并发集合类**：
        - `ConcurrentHashMap`：分段锁或CAS实现高并发。
        - `CopyOnWriteArrayList`：写时复制，适合读多写少。
        - `BlockingQueue`：阻塞队列（如`LinkedBlockingQueue`）。

---

#### **6. 性能与使用场景**

| 场景     | 推荐集合类               | 理由                           |
|--------|---------------------|------------------------------|
| 快速随机访问 | `ArrayList`         | 基于数组，索引访问O(1)                |
| 频繁增删元素 | `LinkedList`        | 链表结构，增删O(1)                  |
| 去重     | `HashSet`           | 哈希表实现去重，O(1)查询               |
| 排序需求   | `TreeSet`/`TreeMap` | 红黑树实现自然或自定义排序                |
| 高并发环境  | `ConcurrentHashMap` | 分段锁或CAS优化并发性能                |
| LRU缓存  | `LinkedHashMap`     | 通过`accessOrder=true`实现最近最少使用 |

---

#### **7. 注意事项**

1. **`fail-fast`机制**：
    - 迭代集合时，若直接修改集合结构（非通过`Iterator.remove()`），会抛出`ConcurrentModificationException`。

2. **初始容量与负载因子**：
    - `HashMap`默认初始容量16，负载因子0.75。容量不足时自动扩容（2倍）。

3. **`equals()`与`hashCode()`**：
    - 使用自定义对象作为`HashMap`的键时，必须重写`equals()`和`hashCode()`。

4. **空值处理**：
    - `HashSet`/`HashMap`允许`null`，`TreeSet`/`TreeMap`不允许（需实现排序逻辑）。

---

#### **8. 总结**

Java集合框架是处理数据结构的核心工具，选择时需综合考虑：

- **数据特性**：有序性、唯一性、键值对需求。
- **性能需求**：增删、查询、排序效率。
- **线程安全**：是否在多线程环境下使用。
- **扩展性**：是否需要自定义排序或LRU缓存等高级功能。

通过合理选择集合类，能显著提升代码效率和可维护性。