# kronos-example-solon

-------------------------

English | [简体中文](https://github.com/Kronos-orm/kronos-example-solon/blob/main/README-zh_CN.md)

This is a sample project based on Solon + Kronos ORM + JDK 17 + Maven + Kotlin 2.0.0.

If you would like to learn more about Kronos, please visit [Kronos](https://www.kotlinorm.com/).

## Introducing Maven dependencies

**1. Add Kronos dependency**

```xml

<dependencies>
    <dependency>
        <groupId>com.kotlinorm</groupId>
        <artifactId>kronos-core</artifactId>
        <version>${kronos.version}</version>
    </dependency>
</dependencies>
```

**2. Add Kotlin compiler plugin**

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

## Configure the data source

The sample project uses kronos-jdbc-wrapper, and you can configure the data source in the following way.
You can also replace it with another wrapper or customize the wrapper.

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

## Run the project

After running the project, visit the following URL to view the results:

```
http://localhost:8081
```

If the interface returns the results shown below, Kronos has run successfully and the compiler plugin is working
properly.

![screen](https://github.com/Kronos-orm/kronos-example-solon/blob/main/screenshot/img.png?raw=true)
