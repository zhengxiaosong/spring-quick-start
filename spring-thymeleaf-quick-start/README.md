# 如何建立web项目

本例简单演示了如何建立web项目，并使用thymeleaf页面模板。
如何单纯需要建立api项目，只需要引入 spring-boot-starter-web 依赖包即可以。


要采用thymeleaf页面模板，就需要再引入 spring-boot-starter-thymeleaf 。同时在resouces资源目录下建立templates目录存放模板文件。


同时本例还演示了如何引入 spring-boot-starter-security 做权限限制。引入后，在配置文件中配置好默认的用户密码：
```yaml
spring:
  security:
    user:
      name: admin
      password: "`123Qwe"
```