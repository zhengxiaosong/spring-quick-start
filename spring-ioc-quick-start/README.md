# 如何使用spring的IoC容器

本例采用最小依赖，实现spring的自动装配功能。需要的依赖为：
```xml
<dependencies>
    <!-- 支持自动装配 -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
    </dependency>
</dependencies>
```
引入改依赖后，在主程序上指定spring bean的扫描路径，生成ioc容器，即可通过ioc容器获取各个bean:
```java
// 指定bean的扫描路径
@ComponentScan("org.example.spring.ioc.quickstart.v3")
public class IocTest {

    private static final Log log = LogFactory.getLog(IocTest.class);

    public static void main(String[] args) {
        // 获取IoC容器
        ApplicationContext context = new AnnotationConfigApplicationContext(IocTest.class);
        // 从容器中获取bean
        IMember member = context.getBean(IMember.class);
        String result = member.register();
        log.info(result);

    }
}
```

本例同样提供了spring项目的肥包打包方式，即是在pom.xml文件中配置好build节点：
```xml
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>${spring.maven.plugin.version}</version>
                <executions>
                    <!-- 打包 -->
                    <execution>
                        <goals><goal>repackage</goal></goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```