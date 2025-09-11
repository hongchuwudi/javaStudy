Java中的`LinkedList`是一个重要的集合类，基于**双向链表**实现，同时实现了`List`和`Deque`接口。以下是其核心知识点的总结：

---

### **1. 概述**

- **数据结构**：双向链表，每个节点（`Node`）包含数据、前驱（`prev`）和后继（`next`）引用。
- **特点**：
    - 插入和删除高效（时间复杂度`O(1)`，但需先定位到位置，整体为`O(n)`）。
    - 随机访问效率低（时间复杂度`O(n)`）。
    - 支持`null`元素。
    - **非线程安全**，需手动同步或使用并发容器。

---

### **2. 类结构**

```java
public class LinkedList<E>
    extends AbstractSequentialList<E>
    implements List<E>, Deque<E>, Cloneable, Serializable
```

- 实现了`List`（列表操作）、`Deque`（双端队列操作）、`Cloneable`（浅拷贝）、`Serializable`（序列化）。

---

### **3. 构造方法**

1. **`LinkedList()`**：空链表。
2. **`LinkedList(Collection<? extends E> c)`**：用现有集合初始化。

---

### **4. 常用方法**

#### **添加元素**

- **`add(E e)`**：尾部添加（等价于`addLast`）。
- **`addFirst(E e)`** / **`addLast(E e)`**：头/尾插入。
- **`offer(E e)`** / **`offerFirst(E e)`** / **`offerLast(E e)`**：同`add`方法（因链表无容量限制，始终返回`true`）。

#### **删除元素**

- **`remove()`** / **`removeFirst()`**：删除头节点（空链表抛异常）。
- **`removeLast()`**：删除尾节点（空链表抛异常）。
- **`poll()`** / **`pollFirst()`** / **`pollLast()`**：删除头/尾节点（空链表返回`null`）。
- **`clear()`**：清空链表。

#### **访问元素**

- **`get(int index)`**：按索引访问（需遍历，效率低）。
- **`getFirst()`** / **`getLast()`**：获取头/尾元素（空链表抛异常）。
- **`peek()`** / **`peekFirst()`** / **`peekLast()`**：获取头/尾元素（空链表返回`null`）。

#### **其他操作**

- **`contains(Object o)`**：是否包含元素。
- **`size()`**：返回元素数量。
- **`indexOf(Object o)`** / **`lastIndexOf(Object o)`**：查找元素位置。

---

### **5. 遍历方式**

1. **普通for循环**：效率低（每次从头遍历）。
2. **增强for循环**：底层使用迭代器。
3. **迭代器**：
    - **`Iterator`**：单向遍历。
    - **`ListIterator`**：双向遍历（支持`previous()`）。
4. **`forEach()` + Lambda**：简洁但性能与迭代器相同。

---

### **6. 作为双端队列（Deque）**

- **栈操作**：`push(E e)`（头插）、`pop()`（头删）。
- **队列操作**：`offer(E e)`（尾插）、`poll()`（头删）。
- **双端操作**：支持`offerFirst`、`offerLast`、`pollFirst`、`pollLast`等。

---

### **7. 性能分析**

| 操作              | 时间复杂度 | 说明          |
|-----------------|-------|-------------|
| 插入/删除（头/尾）      | O(1)  | 直接操作头尾节点。   |
| 插入/删除（中间）       | O(n)  | 需遍历定位到目标位置。 |
| 随机访问（`get/set`） | O(n)  | 需遍历链表。      |

---

### **8. 与ArrayList对比**

| 特性    | LinkedList | ArrayList |
|-------|------------|-----------|
| 底层结构  | 双向链表       | 动态数组      |
| 随机访问  | 慢（O(n)）    | 快（O(1)）   |
| 头插/头删 | 快（O(1)）    | 慢（O(n)）   |
| 内存占用  | 更高（存储指针）   | 更低（连续内存）  |
| 适用场景  | 频繁增删       | 频繁访问/尾部操作 |

---

### **9. 线程安全**

- **非线程安全**，多线程环境下需通过以下方式同步：
    - **`Collections.synchronizedList(new LinkedList<>())`**。
    - 使用并发容器（如`ConcurrentLinkedQueue`）。

---

### **10. 使用场景**

- 频繁在头尾插入/删除元素。
- 实现栈、队列或双端队列。
- 不需要频繁随机访问的场景。

---

### **11. 注意事项**

- **避免随机访问**：尽量使用迭代器遍历。
- **快速失败机制**：迭代过程中修改结构会触发`ConcurrentModificationException`。
- **克隆与序列化**：`clone()`为浅拷贝；支持序列化。

---

通过理解`LinkedList`的底层实现和适用场景，可以更高效地选择数据结构，优化程序性能。