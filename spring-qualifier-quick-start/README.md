# spring-qualifier-quick-start 说明

本项目演示了如何通过自定义条件注解和配置文件，实现 Spring Bean 的按需注入。适用于如多数据源等需根据环境灵活切换 Bean 实现的场景。

## 实现逻辑

1. **自定义条件注解**  
   - 定义了 `@AbroadComponent` 和 `@DomesticComponent` 注解，分别用于标记“国外”与“国内”实现类。
   - 这两个注解本质上是对 `@Component` 的封装，并结合了各自的条件（`AbroadCondition`、`DomesticCondition`）。

2. **条件类实现**  
   - `AbroadCondition` 和 `DomesticCondition` 分别实现 Spring 的 `Condition` 接口。
   - 它们根据配置文件中的 `quickstart-qualifier-area` 值（`abroad` 或 `domestic`）判断当前环境，决定对应 Bean 是否注入。

3. **配置文件驱动**  
   - 在配置文件（如 `application.properties`）中通过设置 `quickstart-qualifier-area=domestic` 或 `abroad`，即可切换注入的 Bean 实现。

4. **使用方式**  
   - 只需在实现类上添加 `@AbroadComponent` 或 `@DomesticComponent` 注解，Spring 会根据配置自动选择注入对应的 Bean。

## 应用场景

- 适用于底层实现（如数据源、服务接口等）需根据环境灵活切换的场景。
- 通过配置文件即可无缝切换 Bean，无需修改业务代码。

## 主要步骤

1. 定义条件注解和条件类。
2. 在实现类上使用自定义注解。
3. 在配置文件中指定 `quickstart-qualifier-area`。
4. 启动应用，Spring 自动根据条件注入对应 Bean。

---

如需详细代码示例，请参考项目源码。
