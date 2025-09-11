在Java中，当你使用 `packet.getAddress().getHostAddress()` 获取IP地址时，返回的是IPv6格式的地址，可能是因为：

1. **你的操作系统或网络环境优先使用IPv6** - 如果客户端和服务器都在支持IPv6的环境中运行，Java可能会默认使用IPv6地址。

2. **Java默认偏好IPv6** - 从Java 1.4开始，Java会优先尝试使用IPv6（如果系统支持）。你可以通过设置系统属性来改变这个行为：
   ```java
   System.setProperty("java.net.preferIPv4Stack", "true");  // 强制使用IPv4
   ```

### 如何强制使用IPv4？

如果你希望始终显示IPv4地址，可以在程序启动时设置JVM参数：

```java
java -Djava.net.preferIPv4Stack=true YourProgram
```

或者在代码中设置：

```java
System.setProperty("java.net.preferIPv4Stack", "true");
```

### 检查是否是IPv6地址

IPv6地址通常包含冒号（`:`），例如 `2001:0db8:85a3::8a2e:0370:7334`，而IPv4是点分十进制格式（如 `192.168.1.1`
）。你可以通过判断地址中是否包含冒号来区分：

```java
String ip = packet.getAddress().getHostAddress();
if (ip.contains(":")) {
    System.out.println("这是IPv6地址: " + ip);
} else {
    System.out.println("这是IPv4地址: " + ip);
}
```

### 总结

- 如果客户端和服务器都支持IPv6，Java可能会返回IPv6地址。
- 通过设置 `java.net.preferIPv4Stack=true` 可以强制使用IPv4。
- 检查IP地址格式（是否包含冒号）可以判断是IPv4还是IPv6。

希望这能帮到你！如果仍有疑问，可以检查你的网络环境是否启用了IPv6。