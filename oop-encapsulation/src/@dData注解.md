# Java中的`@Data`注解详解

`@Data`是Project Lombok提供的一个组合注解，用于简化Java类的编写，自动生成常见的样板代码。

## 基本概念

1. **来源**：属于Lombok项目
2. **作用**：自动生成以下内容：
    - 所有字段的getter方法
    - 非final字段的setter方法
    - `toString()`方法
    - `equals()`和`hashCode()`方法
    - 无参构造函数（如果没有显式定义其他构造函数）

## 使用示例

```java
import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String email;
    private final String role = "USER"; // final字段不会生成setter
}
```

等效于手动编写：

```java
public class User {
    private Long id;
    private String username;
    private String email;
    private final String role = "USER";

    // 自动生成的代码
    public User() {}
    
    public Long getId() { return this.id; }
    public String getUsername() { return this.username; }
    public String getEmail() { return this.email; }
    public String getRole() { return this.role; }
    
    public void setId(Long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    
    @Override
    public boolean equals(Object o) { /* 自动生成的equals实现 */ }
    
    @Override
    public int hashCode() { /* 自动生成的hashCode实现 */ }
    
    @Override
    public String toString() { /* 自动生成的toString实现 */ }
}
```

## 主要特点

1. **组合注解**：相当于以下Lombok注解的组合：
    - `@Getter`
    - `@Setter`
    - `@ToString`
    - `@EqualsAndHashCode`
    - `@RequiredArgsConstructor`

2. **可配置性**：可以通过参数自定义行为
   ```java
   @Data(staticConstructor = "of") // 生成静态工厂方法而不是构造器
   public class Point {
       private int x, y;
   }
   ```

3. **继承行为**：
    - 默认不调用父类的`equals()`/`hashCode()`
    - 可以使用`@EqualsAndHashCode(callSuper = true)`覆盖此行为

## 使用场景

1. POJO类（如DTO、VO、Entity等）
2. 需要大量样板代码的简单类
3. 原型开发阶段快速迭代

## 注意事项

1. **Lombok依赖**：需要添加Lombok依赖和IDE插件支持
    - Maven依赖：
      ```xml
      <dependency>
          <groupId>org.projectlombok</groupId>
          <artifactId>lombok</artifactId>
          <version>1.18.24</version>
          <scope>provided</scope>
      </dependency>
      ```

2. **不可变类**：如果需要创建不可变类，应使用`@Value`注解代替

3. **继承问题**：默认生成的`equals()`和`hashCode()`不考虑父类字段

4. **调试**：生成的代码在编译时存在，但源代码中不可见

5. **与其他注解组合**：
    - 可与`@Builder`组合使用
    - 避免与显式定义的方法冲突

`@Data`注解极大简化了Java开发中样板代码的编写，使代码更加简洁易读，是现代Java开发中常用的工具之一。