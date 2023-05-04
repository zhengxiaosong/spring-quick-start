# 如何使用配置文件

本例采用最小依赖，示例如何在spring框架下使用配置文件：
```xml
    <dependencies>
        <!-- 支持自动装配，并自动注入配置文件 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot</artifactId>
        </dependency>
        <!-- 采用yaml形式的配置文件需要加入以下依赖 -->
        <dependency>
            <groupId>org.yaml</groupId>
            <artifactId>snakeyaml</artifactId>
        </dependency>
    </dependencies>
```
单纯需要自动装配的话，只需要引入spring-context包，但如果想要能够自动注入配置文件，就需要引入spring-boot包。
默认的spring-boot只支持 *.properties 形式的配置文件，如果想要采用yaml 的配置方式的话，那么需要加上snakeyaml依赖包。