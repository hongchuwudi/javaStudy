### Java 网络编程：UDP 与 TCP 编程总结

---

#### **一、UDP 编程（无连接、不可靠、面向数据报）**

##### **核心特点**

- **无连接**：发送端和接收端无需建立连接。
- **不可靠传输**：数据可能丢失、乱序或重复。
- **数据报模式**：以数据包（`DatagramPacket`）为单位传输，大小受限（通常 ≤ 64KB）。
- **高效**：适用于实时性要求高、允许少量丢包的场景（如视频通话、在线游戏）。

---

##### **核心类**

| 类                | 作用                      |
|------------------|-------------------------|
| `DatagramSocket` | 用于发送或接收 UDP 数据包的 Socket |
| `DatagramPacket` | 封装数据包（包含数据、目标地址和端口）     |

---

##### **UDP 发送端代码示例**

```java
// 1. 创建发送端 Socket（无需绑定端口）
DatagramSocket socket = new DatagramSocket();

// 2. 准备数据包（数据 + 目标 IP 和端口）
byte[] data = "Hello UDP".getBytes();
InetAddress address = InetAddress.getByName("127.0.0.1");
DatagramPacket packet = new DatagramPacket(data, data.length, address, 8888);

// 3. 发送数据包
socket.send(packet);

// 4. 关闭资源
socket.close();
```

##### **UDP 接收端代码示例**

```java
// 1. 创建接收端 Socket（需绑定端口）
DatagramSocket socket = new DatagramSocket(8888);

// 2. 准备空数据包接收数据
byte[] buffer = new byte[1024];
DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

// 3. 阻塞接收数据
socket.receive(packet);

// 4. 解析数据
String msg = new String(packet.getData(), 0, packet.getLength());
System.out.println("收到来自 " + packet.getAddress() + " 的消息: " + msg);

// 5. 关闭资源
socket.close();
```

---

#### **二、TCP 编程（面向连接、可靠、字节流传输）**

##### **核心特点**

- **面向连接**：需通过三次握手建立连接（`connect()` 和 `accept()`）。
- **可靠传输**：通过确认、重传、流量控制等机制保证数据有序到达。
- **字节流模式**：数据以字节流形式传输，无大小限制。
- **适用场景**：文件传输、HTTP 请求等需可靠性的场景。

---

##### **核心类**

| 类              | 作用                  |
|----------------|---------------------|
| `ServerSocket` | 服务端监听端口，等待客户端连接     |
| `Socket`       | 客户端连接服务端，或服务端与客户端通信 |

---

##### **TCP 服务端代码示例**

```java
// 1. 创建服务端 Socket，绑定端口
ServerSocket serverSocket = new ServerSocket(8888);

// 2. 监听客户端连接（阻塞等待）
Socket socket = serverSocket.accept();
System.out.println("客户端已连接: " + socket.getRemoteSocketAddress());

// 3. 获取输入流读取客户端数据
InputStream is = socket.getInputStream();
BufferedReader reader = new BufferedReader(new InputStreamReader(is));
String msg = reader.readLine();
System.out.println("收到消息: " + msg);

// 4. 获取输出流向客户端发送数据
OutputStream os = socket.getOutputStream();
os.write("Hello TCP Client".getBytes());

// 5. 关闭资源（先关流，再关 Socket）
reader.close();
is.close();
os.close();
socket.close();
serverSocket.close();
```

##### **TCP 客户端代码示例**

```java
// 1. 创建客户端 Socket，连接服务端
Socket socket = new Socket("127.0.0.1", 8888);

// 2. 获取输出流向服务端发送数据
OutputStream os = socket.getOutputStream();
os.write("Hello TCP Server".getBytes());

// 3. 获取输入流读取服务端响应
InputStream is = socket.getInputStream();
BufferedReader reader = new BufferedReader(new InputStreamReader(is));
String reply = reader.readLine();
System.out.println("服务端响应: " + reply);

// 4. 关闭资源
os.close();
is.close();
socket.close();
```

---

#### **三、UDP 与 TCP 对比**

| **特性** | **UDP**      | **TCP**         |
|--------|--------------|-----------------|
| 连接方式   | 无连接          | 面向连接            |
| 可靠性    | 不可靠（可能丢包、乱序） | 可靠（有序、确认重传机制）   |
| 传输模式   | 数据报（包）       | 字节流（流式）         |
| 效率     | 高（无连接开销）     | 较低（需维护连接状态）     |
| 适用场景   | 实时通信、广播、多播   | 文件传输、HTTP、数据库访问 |
| 数据大小限制 | 单包 ≤ 64KB    | 无限制             |

---

#### **四、关键注意事项**

##### **UDP**

1. **数据包大小**：单次发送数据不宜超过 64KB，否则可能被丢弃或分片。
2. **多线程接收**：可在同一端口绑定多个 `DatagramSocket` 实现并发处理。
3. **广播与多播**：
    - **广播**：发送到 `255.255.255.255`。
    - **多播**：使用 `MulticastSocket` 加入多播组（IP 范围：`224.0.0.0` ~ `239.255.255.255`）。

##### **TCP**

1. **粘包与拆包**：
    - **问题**：TCP 是流式传输，多次发送的数据可能被合并或拆分。
    - **解决方案**：定义固定长度的消息头、使用分隔符（如 `\n`）、或使用协议（如 HTTP 的 `Content-Length`）。
2. **多客户端处理**：
    - 服务端需为每个客户端连接创建新线程（或使用线程池）。
3. **资源释放**：
    - 关闭顺序：先关流，再关 `Socket` 和 `ServerSocket`。

---

#### **五、最佳实践**

- **UDP**：适合实时性要求高、容忍少量丢包的场景（如 DNS 查询、实时音视频）。
- **TCP**：适合需要可靠传输的场景（如文件下载、Web 请求）。
- **性能优化**：
    - UDP：减少数据包大小，避免分片。
    - TCP：使用 NIO（`SocketChannel`）实现非阻塞通信，提升并发性能。

掌握两者差异，根据业务需求选择合适的协议！