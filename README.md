# spring 上手实例

本项目为 Spring 技术栈的快速上手与实战示例集合，涵盖了 Spring 核心功能及常用扩展的典型用法，适合初学者和有经验的开发者查阅和参考。

## 子模块说明

- **spring-ioc-quick-start**：Spring IoC（控制反转）基础用法示例。
- **spring-aop-quick-start**：Spring AOP（面向切面编程）快速入门。
- **spring-event-quick-start**：Spring 事件机制（Event）示例，包含自定义事件、监听器、服务类事件发布等。
- **spring-properties-quick-start**：Spring 配置文件与属性注入示例。
- **spring-mybatis-quick-start**：Spring 集成 MyBatis 的基础用法。
- **spring-kafka-quick-start**：Spring 集成 Kafka 消息队列的基础用法。
- **spring-drools-quick-start**：Spring 集成 Drools 规则引擎的基础用法。
- **spring-qualifier-quick-start**：@Qualifier 注解的使用示例。
- **spring-test-quick-start**：Spring 测试相关用法。
- **spring-thymeleaf-quick-start**：Spring 集成 Thymeleaf 模板引擎的基础用法。
- **spring-webapi-quick-start**：Spring Web API（RESTful 接口）开发基础。

## 运行方式

1. 进入对应子模块目录，例如 `cd spring-event-quick-start`。
2. 使用 Maven 启动：
   ```bash
   mvn spring-boot:run
   ```
   或在 IDE 中直接运行主类。
3. 参考各子模块下的 README.md 获取详细说明和示例代码。

## 环境要求

- JDK 8 及以上
- Maven 3.x
- 推荐使用 IntelliJ IDEA 或 Eclipse

## 说明

每个子模块均为独立的 Spring Boot 工程，互不依赖，可单独运行和学习。
