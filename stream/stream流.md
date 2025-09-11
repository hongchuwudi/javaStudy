以下是 Java Stream 的全面总结，涵盖核心概念、操作方法及使用场景：

---

### **一、Stream 概述**

- **定义**：Java 8 引入的 API，用于以声明式（函数式）处理集合数据，支持顺序和并行操作。
- **特点**：
    - **不存储数据**：仅对数据源（如集合、数组）进行操作。
    - **不修改源数据**：生成新流。
    - **延迟执行**：终端操作触发执行。
    - **可消费性**：流只能被使用一次。

---

### **二、创建 Stream**

1. **集合**：`collection.stream()` 或 `collection.parallelStream()`
2. **数组**：`Arrays.stream(array)`
3. **直接创建**：
    - `Stream.of(T...)`
    - `Stream.iterate(初始值, 函数)`（如生成序列）
    - `Stream.generate(Supplier)`（如生成随机数）
4. **文件/IO**：`Files.lines(Path)`（需关闭）
5. **原始类型流**：
    - `IntStream`、`LongStream`、`DoubleStream`
    - 示例：`IntStream.range(1, 10)`

---

### **三、中间操作（Intermediate Operations）**

- **筛选与切片**：
    - `filter(Predicate)`：过滤元素。
    - `distinct()`：去重。
    - `limit(n)`：保留前 n 个元素。
    - `skip(n)`：跳过前 n 个元素。

- **映射**：
    - `map(Function)`：元素一对一转换。
    - `flatMap(Function)`：扁平化流（如合并多个集合）。

- **排序与探查**：
    - `sorted()` / `sorted(Comparator)`：排序。
    - `peek(Consumer)`：调试用，查看流元素。

---

### **四、终端操作（Terminal Operations）**

1. **遍历与匹配**：
    - `forEach(Consumer)`：遍历元素。
    - `anyMatch(Predicate)` / `allMatch()` / `noneMatch()`：返回布尔。
    - `findFirst()` / `findAny()`：返回 `Optional<T>`。

2. **归约与统计**：
    - `reduce(BinaryOperator)`：将流归约为单个值（如求和）。
    - `count()`：元素总数。
    - `min()` / `max()`：返回 `Optional<T>`。

3. **收集结果**：
    - `collect(Collector)`：转换为集合或其他结构。
        - **常用收集器**（`Collectors` 类）：
            - `toList()`、`toSet()`、`toMap()`
            - `joining()`：拼接字符串。
            - `groupingBy(Function)`：分组。
            - `partitioningBy(Predicate)`：分区（true/false）。
            - `summingInt()`, `averagingDouble()`：统计计算。

---

### **五、特性与优化**

1. **并行流**：
    - 使用 `parallelStream()` 或 `stream().parallel()` 开启。
    - 需确保操作线程安全，避免共享变量。
    - 数据量大时可能提升性能，小数据可能更慢。

2. **短路操作**：
    - `limit()`、`findFirst()`、`anyMatch()` 等可提前终止处理。

3. **原始类型流优化**：
    - `IntStream`、`LongStream` 等避免装箱拆箱，提升性能。

---

### **六、注意事项**

1. **流不可复用**：触发终端操作后流即关闭，再次使用会抛异常。
2. **避免副作用**：中间操作应保持无状态（如不修改外部变量）。
3. **资源管理**：`Files.lines()` 等需用 `try-with-resources` 确保关闭。
4. **空值处理**：`Optional` 类处理可能为 `null` 的结果（如 `findFirst()`）。

---

### **七、示例代码**

```java
// 示例：统计大于5的偶数数量
long count = list.stream()
    .filter(n -> n % 2 == 0)
    .filter(n -> n > 5)
    .count();

// 示例：将名字转为大写并收集为List
List<String> names = people.stream()
    .map(person -> person.getName().toUpperCase())
    .collect(Collectors.toList());

// 示例：分组（按部门分组员工）
Map<String, List<Employee>> deptMap = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment));
```

---

### **八、适用场景**

- **简化集合操作**：链式调用替代多层循环。
- **大数据处理**：并行流加速计算。
- **函数式编程**：结合 Lambda 表达式提高代码可读性。

掌握 Stream API 可显著提升 Java 集合操作的简洁性与效率！