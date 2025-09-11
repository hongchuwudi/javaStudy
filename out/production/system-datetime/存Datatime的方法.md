在 Java 中，将字符串 `"2023-07-22 11:11:12"` 转换为日期对象并存储，推荐使用 Java 8 引入的 `java.time` 包中的日期时间类。以下是几种常见的方法：

---

## **方法 1：使用 `LocalDateTime`（推荐）**

`LocalDateTime` 适合存储不带时区的日期和时间。

### **步骤**

1. 定义与字符串格式匹配的 `DateTimeFormatter`
2. 使用 `LocalDateTime.parse()` 方法解析字符串

### **代码示例**

```java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateTime {
    public static void main(String[] args) {
        String dateStr = "2023-07-22 11:11:12";
        
        // 定义格式（必须和字符串格式匹配）
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        // 解析字符串为 LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
        
        System.out.println("解析后的 LocalDateTime: " + dateTime);
    }
}
```

**输出：**

```
解析后的 LocalDateTime: 2023-07-22T11:11:12
```

---

## **方法 2：使用 `ZonedDateTime`（带时区）**

如果字符串包含时区信息（如 `2023-07-22 11:11:12+08:00`），可以使用 `ZonedDateTime`。

### **代码示例**

```java
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class StringToZonedDateTime {
    public static void main(String[] args) {
        String dateStr = "2023-07-22 11:11:12+08:00";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssXXX");
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateStr, formatter);

        System.out.println("解析后的 ZonedDateTime: " + zonedDateTime);
    }
}
```

**输出：**

```
解析后的 ZonedDateTime: 2023-07-22T11:11:12+08:00
```

---

## **方法 3：转换为 `java.util.Date`（旧 API，不推荐）**

如果需要兼容旧代码，可以转换为 `java.util.Date`，但建议尽量使用 `java.time`。

### **代码示例**

```java
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.time.format.DateTimeFormatter;

public class StringToUtilDate {
    public static void main(String[] args) {
        String dateStr = "2023-07-22 11:11:12";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, formatter);

        // 转换为 java.util.Date（需指定时区）
        Date utilDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        System.out.println("转换后的 java.util.Date: " + utilDate);
    }
}
```

**输出：**

```
转换后的 java.util.Date: Sat Jul 22 11:11:12 CST 2023
```

---

## **总结**

| 方法               | 适用场景      | 推荐程度      |
|------------------|-----------|-----------|
| `LocalDateTime`  | 不带时区的日期时间 | ⭐⭐⭐⭐⭐（推荐） |
| `ZonedDateTime`  | 带时区的日期时间  | ⭐⭐⭐⭐      |
| `java.util.Date` | 兼容旧代码     | ⭐（不推荐）    |

### **最佳实践**

✅ **优先使用 `LocalDateTime`**（除非需要时区）  
✅ **使用 `DateTimeFormatter` 定义格式**（确保匹配字符串格式）  
❌ **避免直接使用 `SimpleDateFormat` 和 `java.util.Date`**（线程不安全，已过时）

这样，你就可以正确存储和操作日期时间数据了！ 🚀