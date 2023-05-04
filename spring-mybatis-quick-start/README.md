# 如何引入mybatis访问数据库

本例引入了 mybatis-spring-boot-starter 包来支持数据访问。
本例演示了连接mysql 数据库，数据需要引入mysql 的jdbc依赖包：
```xml
<!--mysql连接-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.39</version>
</dependency>
```
如果是采用了 mssql ，则需要替换成 mssql的jdbc依赖包：
```xml
<!-- 集成SQL Server -->
<dependency>
    <groupId>com.microsoft.sqlserver</groupId>
    <artifactId>mssql-jdbc</artifactId>
</dependency>
```

引入依赖包后，需要在yaml配置文件中指定数据源，超时连接等配置：
```yaml
## spring-boot-configuration
spring:
  datasource:
    driverClassName: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/study?useSSL=false&serverTimezone=UTC
    username: dbAdmin
    password: 1qaz@WSX
    maxWait: 1800000
```

SQL Server 的配置形式如下：
```yaml
spring:
  datasource:
    driverClassName: com.microsoft.sqlserver.jdbc.SQLServerDriver
    url: jdbc:sqlserver://;serverName=${ip};port=${port};databaseName=${database}
    username: ${username}
    password: ${password}
    maxWait: 1800000
```
同时需要指定mybatis的xml路径：
```yaml
mybatis:
  mapper-locations: classpath:mapper/*.xml
  configuration:
    jdbc-type-for-null: 'null'
```

本例还引入了 lombok 来简化 pojo 的编写。只需要引入依赖包，并在pojo的类上加上 @Data 注解就可以。