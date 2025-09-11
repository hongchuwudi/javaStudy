---

### **Java 文件操作全面总结**

---

#### **一、核心类与接口**

1. **传统 `java.io` 包**：
    - **`File` 类**：表示文件或目录路径（不推荐，功能有限）。
    - **流（Stream）**：
        - **字节流**：`InputStream` / `OutputStream`（如 `FileInputStream`）。
        - **字符流**：`Reader` / `Writer`（如 `FileReader`，支持字符编码）。

2. **NIO.2（Java 7+）**：
    - **`Path` 接口**：替代 `File`，表示文件系统路径。
    - **`Files` 工具类**：提供静态方法操作文件（推荐使用）。
    - **`Paths` 工具类**：创建 `Path` 对象（`Path path = Paths.get("file.txt")`）。
    - **`FileSystems`**：访问文件系统（如获取默认文件系统）。

---

#### **二、基本文件操作**

1. **创建文件/目录**：
   ```java
   // 创建文件
   Path newFile = Files.createFile(Paths.get("test.txt"));

   // 创建目录（单层或多层）
   Files.createDirectory(Paths.get("dir"));           // 单层目录
   Files.createDirectories(Paths.get("a/b/c"));       // 多层目录
   ```

2. **删除文件/目录**：
   ```java
   Files.delete(path);                     // 若文件不存在，抛 NoSuchFileException
   Files.deleteIfExists(path);             // 安全删除
   ```

3. **复制/移动文件**：
   ```java
   // 复制
   Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

   // 移动（重命名）
   Files.move(sourcePath, targetPath, StandardCopyOption.ATOMIC_MOVE);
   ```

4. **检查文件属性**：
   ```java
   boolean exists = Files.exists(path);
   boolean isDir = Files.isDirectory(path);
   boolean isReadable = Files.isReadable(path);
   long size = Files.size(path);           // 文件大小（字节）
   ```

5. **文件时间**：
   ```java
   FileTime lastModified = Files.getLastModifiedTime(path);
   Files.setLastModifiedTime(path, FileTime.from(Instant.now()));
   ```

---

#### **三、文件读写**

1. **读取文件内容**：
    - **全部读取（小文件）**：
      ```java
      // 字节形式
      byte[] bytes = Files.readAllBytes(path);
 
      // 字符形式（指定编码）
      List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
      ```

    - **逐行读取（大文件）**：
      ```java
      try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
          stream.forEach(System.out::println);
      }
      ```

2. **写入文件内容**：
    - **覆盖写入**：
      ```java
      Files.write(path, content.getBytes());                // 字节
      Files.write(path, lines, StandardCharsets.UTF_8);     // 字符行
      ```

    - **追加写入**：
      ```java
      Files.write(path, content.getBytes(), StandardOpenOption.APPEND);
      ```

3. **缓冲流读写（高性能）**：
   ```java
   try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
       String line;
       while ((line = reader.readLine()) != null) {
           // 处理每行
       }
   }

   try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
       writer.write("Hello World");
   }
   ```

---

#### **四、目录操作**

1. **遍历目录**：
   ```java
   try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath)) {
       for (Path entry : stream) {
           System.out.println(entry.getFileName());
       }
   }
   ```

2. **递归遍历目录树**：
   ```java
   Files.walk(dirPath)
        .filter(Files::isRegularFile)
        .forEach(System.out::println);
   ```

3. **查找文件**：
   ```java
   Path foundFile = Files.find(dirPath, Integer.MAX_VALUE, 
       (path, attrs) -> path.endsWith(".txt") && attrs.size() > 1024)
       .findFirst().orElse(null);
   ```

---

#### **五、高级特性**

1. **临时文件**：
   ```java
   Path tempFile = Files.createTempFile("prefix", ".suffix");  // 自动生成唯一文件名
   tempFile.toFile().deleteOnExit();                          // JVM 退出时删除
   ```

2. **文件锁（并发控制）**：
   ```java
   try (FileChannel channel = FileChannel.open(path, StandardOpenOption.WRITE);
        FileLock lock = channel.lock()) {     // 排他锁
       // 操作文件
   }
   ```

3. **内存映射文件（高性能随机访问）**：
   ```java
   try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {
       MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
       // 直接操作缓冲区
   }
   ```

---

#### **六、异常处理与资源管理**

1. **捕获 `IOException`**：
   ```java
   try {
       Files.copy(source, target);
   } catch (IOException e) {
       e.printStackTrace();
   }
   ```

2. **自动关闭资源**：
   ```java
   try (InputStream is = Files.newInputStream(path)) {  // 自动调用 close()
       // 使用流
   }
   ```

---

#### **七、最佳实践**

1. **优先使用 NIO.2（`Path` 和 `Files`）**：比传统 `File` 更强大且不易出错。
2. **处理字符编码**：始终显式指定（如 `StandardCharsets.UTF_8`）。
3. **资源释放**：使用 `try-with-resources` 确保流/通道正确关闭。
4. **性能敏感场景**：对大文件使用缓冲流或内存映射。

---

#### **示例：综合应用**

```java
// 复制所有 .txt 文件到目标目录
Path sourceDir = Paths.get("source");
Path targetDir = Paths.get("target");

Files.walk(sourceDir)
     .filter(Files::isRegularFile)
     .filter(p -> p.toString().endsWith(".txt"))
     .forEach(p -> {
         try {
             Path dest = targetDir.resolve(sourceDir.relativize(p));
             Files.createDirectories(dest.getParent());
             Files.copy(p, dest, StandardCopyOption.REPLACE_EXISTING);
         } catch (IOException e) {
             e.printStackTrace();
         }
     });
```

---

掌握这些知识，可以高效处理 Java 中的文件操作需求！