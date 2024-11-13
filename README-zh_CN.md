# kronos-example-solon

-------------------------

[English](https://github.com/Kronos-orm/kronos-example-solon/blob/main/README.md) | 简体中文

这是一个基于Solon + Kronos ORM + JDK 17 + Maven + Kotlin 2.0.0的示例项目。

如果您想了解更多关于Kronos的信息，请访问[Kronos](https://www.kotlinorm.com/)。

## 引入Maven依赖

**1. 添加Kronos依赖**

```xml

<dependencies>
    <dependency>
        <groupId>com.kotlinorm</groupId>
        <artifactId>kronos-core</artifactId>
        <version>${kronos.version}</version>
    </dependency>
</dependencies>
```

**2. 添加Kotlin编译器插件**

```xml

<plugins>
    <plugin>
        <groupId>org.jetbrains.kotlin</groupId>
        <artifactId>kotlin-maven-plugin</artifactId>
        <extensions>true</extensions>
        <configuration>
            <compilerPlugins>
                <plugin>all-open</plugin>
                <plugin>kronos-maven-plugin</plugin>
            </compilerPlugins>
        </configuration>
        <dependencies>
            <dependency>
                <groupId>org.jetbrains.kotlin</groupId>
                <artifactId>kotlin-maven-allopen</artifactId>
                <version>${kotlin.version}</version>
            </dependency>
            <dependency>
                <groupId>com.kotlinorm</groupId>
                <artifactId>kronos-maven-plugin</artifactId>
                <version>${kronos.version}</version>
            </dependency>
        </dependencies>
    </plugin>
</plugins>
```

## 配置数据源

项目中使用了kronos-jdbc-wrapper，您可以按以下方式配置数据源，也可以替换为其他wrapper或自定义wrapper。

```kotlin
@SolonMain
class App

val ds = BasicDataSource().apply {
    url = "jdbc:mysql://localhost:3306/kotlinorm"
    username = "root"
    password = "**********"
}

fun main(args: Array<String>) {
    Kronos.apply {
        dataSource = { KronosBasicWrapper(ds) }
        fieldNamingStrategy = LineHumpNamingStrategy
        tableNamingStrategy = LineHumpNamingStrategy
    }
    Solon.start(App::class.java, args)
}
```

## 运行项目

运行项目后，访问以下URL，即可查看结果：

```
http://localhost:8081
```

如果接口返回的结果如下图所示，则表示Kronos已成功运行，编译器插件也已正常工作。

![screen](https://github.com/Kronos-orm/kronos-example-solon/blob/main/screenshot/img.png?raw=true)
