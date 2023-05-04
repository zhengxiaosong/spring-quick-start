# 如何根据配置文件选择注入spring bean

本例自定义了两个bean注入条件「`AbroadCondition`、`DomesticCondition`」。 
`AbroadCondition`关联了配置文件中 abroad 的相关配置，`Domestic` 关联了配置文件中 domestic 的相关配置。 
当配置文件指定了quickstart-qualifier-area = domestic 时，被 `DomesticCondition` 注解的对象注入到bean实例； 
反之quickstart-qualifier-area = abroad 时，被`AbroadCondition`注解的对象注入到bean实例。 

同时建立两个注解 「`@AbroadComponent`、`@DomesticComponent`」覆盖 `@Component` 注解及
各自的注入条件「`AbroadCondition`、`DomesticCondition`」。
这样， 要注入的对象只需要注解上 `@AbroadComponent` 或 `@DomesticComponent`就可以。

本例可用于底层数据源不同，需要通过配置方式选择数据源的应用。