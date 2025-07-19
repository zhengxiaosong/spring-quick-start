# spring-event-quick-start

本项目是一个基于 Spring 的事件机制（Event）快速入门示例，演示了如何自定义事件、发布事件、监听事件，并通过服务类调用事件发布。

## 主要内容

- **自定义事件**：实现自定义事件类 `DemoEvent`。
- **事件监听器**：实现两个事件监听器 `EventFirstListener` 和 `EventSecondListener`，分别监听并处理自定义事件。
- **事件发布**：在 `Application` 启动类和 `DemoService` 服务类中发布事件，触发监听器响应。
- **服务类调用**：`DemoService` 作为业务服务，演示在业务逻辑中发布事件的典型场景。

## 目录结构

```
src/main/java/org/example/spring/event/quickstart/
    Application.java                // 启动类，发布事件
    events/
        DemoEvent.java              // 自定义事件
        EventFirstListener.java     // 第一个事件监听器
        EventSecondListener.java    // 第二个事件监听器
    service/
        DemoService.java            // 业务服务类，发布事件
```

## 快速开始

1. 克隆或下载本项目到本地。
2. 进入 `spring-event-quick-start` 目录，使用以下命令启动：

   ```bash
   mvn spring-boot:run
   ```

   或在 IDE 中直接运行 `Application.java`。

3. 控制台将输出事件发布和监听器处理的日志。

## 依赖要求

- JDK 8 及以上
- Maven 3.x
- Spring Boot 2.x 及以上

## 参考

- [Spring官方文档-事件](https://docs.spring.io/spring-framework/reference/core/events.html)

---

如有问题欢迎提 issue 交流。
