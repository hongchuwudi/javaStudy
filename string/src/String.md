# Java String 知识全面总结

## 一、String 基础特性

### 1. 不可变性(Immutability)

- **核心特点**：String 对象一旦创建，其内容不可更改
- **实现原理**：
    - 内部使用 `final char[]` (JDK 8及之前) 或 `byte[]` (JDK 9+) 存储字符
    - 所有修改操作都返回新String对象
- **优势**：
    - 线程安全
    - 缓存哈希值(性能优化)
    - 适合作为Map键值

### 2. 字符串常量池(String Pool)

- **位置**：位于堆内存中的特殊存储区域
- **机制**：
    - 直接量赋值(`String s = "abc"`)会检查池中是否存在相同字符串
    - `new String("abc")` 会强制创建新对象
- **手动入池**：`intern()` 方法
  ```java
  String s1 = new String("abc");
  String s2 = s1.intern(); // 返回池中已存在的"abc"引用
  ```

## 二、String 创建方式

| 创建方式                     | 示例                               | 是否检查常量池 | 产生对象数 |
|--------------------------|----------------------------------|---------|-------|
| 直接量赋值                    | `String s = "abc"`               | 是       | 0或1   |
| new 关键字                  | `String s = new String("abc")`   | 否       | 1或2   |
| 字符数组转换                   | `new String(char[])`             | 否       | 1     |
| 字节数组转换                   | `new String(byte[], Charset)`    | 否       | 1     |
| StringBuilder.toString() | `new StringBuilder().toString()` | 否       | 1     |

## 三、String 重要方法

### 1. 基础查询方法

```java
int length()                          // 字符串长度
boolean isEmpty()                     // 是否空字符串
char charAt(int index)                // 获取指定位置字符
int codePointAt(int index)            // 获取指定位置字符的Unicode码点
```

### 2. 比较方法

```java
boolean equals(Object anObject)       // 内容比较
boolean equalsIgnoreCase(String str)  // 忽略大小写比较
int compareTo(String anotherString)   // 字典序比较
int compareToIgnoreCase(String str)   // 忽略大小写字典序比较
```

### 3. 查找方法

```java
boolean contains(CharSequence s)      // 是否包含子串
int indexOf(int ch/String str)        // 首次出现位置
int lastIndexOf(int ch/String str)    // 最后出现位置
boolean startsWith(String prefix)     // 是否以指定前缀开始
boolean endsWith(String suffix)       // 是否以指定后缀结束
```

### 4. 操作与转换方法

```java
String concat(String str)             // 字符串连接
String substring(int beginIndex)      // 子串截取
String[] split(String regex)          // 正则分割
String replace(char old, char new)    // 字符替换
String replace(CharSequence old, CharSequence new) // 字符串替换
String toLowerCase()                  // 转小写
String toUpperCase()                  // 转大写
String trim()                         // 去除首尾空白
String strip()                        // JDK11+ 去除Unicode空白
```

### 5. JDK 8+ 新增方法

```java
String join(CharSequence delimiter, CharSequence... elements) // 字符串连接
boolean isBlank()                     // JDK11+ 是否空白(包括Unicode空白)
String repeat(int count)              // JDK11+ 重复字符串
String formatted(Object... args)      // JDK15+ 格式化字符串
```

## 四、String 性能优化

### 1. 字符串拼接

- **避免使用`+`循环拼接**：产生大量中间String对象
- **推荐使用**：
  ```java
  // 单线程
  StringBuilder sb = new StringBuilder();
  sb.append("a").append("b");
  
  // 多线程
  StringBuffer sbf = new StringBuffer();
  sbf.append("a").append("b");
  ```

### 2. 大量字符串处理

- **考虑使用`char[]`直接操作**：适用于高性能场景
- **正则表达式预编译**：
  ```java
  private static final Pattern PATTERN = Pattern.compile("regex");
  // 复用Pattern对象
  ```

### 3. 内存优化

- **使用`substring`注意**：JDK 6与JDK 7+实现不同，可能引起内存泄漏
- **大文本处理**：考虑使用`CharSequence`接口或流式处理

## 五、String 编码与国际化

### 1. 字符编码

```java
// 编码
byte[] utf8Bytes = "中文".getBytes(StandardCharsets.UTF_8);

// 解码
String str = new String(utf8Bytes, StandardCharsets.UTF_8);
```

### 2. 常见编码问题

- **乱码原因**：编码与解码字符集不一致
- **解决方案**：
    - 明确指定字符集(推荐使用`StandardCharsets`)
    - 避免使用默认平台编码

## 六、String 与其它类的比较

### 1. String vs StringBuilder vs StringBuffer

| 特性   | String  | StringBuilder | StringBuffer    |
|------|---------|---------------|-----------------|
| 可变性  | 不可变     | 可变            | 可变              |
| 线程安全 | 是(天然)   | 否             | 是(synchronized) |
| 性能   | 低(修改操作) | 高             | 中等              |
| 使用场景 | 常量字符串   | 单线程字符串操作      | 多线程字符串操作        |

### 2. String vs CharSequence

- `CharSequence` 是接口，`String` 是其实现类
- 其他实现类：`StringBuilder`, `StringBuffer`, `CharBuffer`

## 七、JDK 重要版本变化

### JDK 6 → JDK 7

- `substring` 实现改变(不再共享原char数组)
- 字符串常量池从永久代移至堆内存

### JDK 8 → JDK 9

- 内部存储从`char[]`改为`byte[]`+编码标记(节省内存)
- 新增紧凑字符串特性(Compact Strings)

### JDK 11 重要新增

- `isBlank()`, `strip()`, `repeat()`等方法
- `lines()` 流式处理多行文本

## 八、最佳实践

1. **字符串比较**：总是使用`equals()`而非`==`
2. **敏感信息**：避免在String中存储，使用后立即清除(char[]更安全)
3. **密码处理**：使用`char[]`而非String(防止内存泄漏暴露)
4. **国际化**：使用`ResourceBundle`而非硬编码字符串
5. **大文本处理**：考虑使用`java.nio.file.Files`读取

## 九、常见面试问题

1. **String为什么设计为不可变？**
    - 安全性(如网络连接、文件路径)
    - 线程安全
    - 缓存哈希值
    - 字符串常量池优化

2. `new String("abc")`创建几个对象？
    - 如果常量池没有"abc"：1个放入常量池，1个堆对象
    - 如果常量池已有"abc"：只创建1个堆对象

3. **如何高效反转字符串？**
   ```java
   // JDK8
   new StringBuilder(str).reverse().toString();
   
   // 自定义实现(更高效)
   char[] chars = str.toCharArray();
   int left = 0, right = chars.length - 1;
   while (left < right) {
       char temp = chars[left];
       chars[left++] = chars[right];
       chars[right--] = temp;
   }
   return new String(chars);
   ```

String是Java中最基础也最重要的类之一，深入理解其特性和实现原理对于编写高效、安全的Java程序至关重要。