# kronos-example-solon

-------------------------

English | [简体中文](https://github.com/Kronos-orm/kronos-example-solon/blob/main/README-zh_CN.md)

This is a sample project based on Solon + Kronos ORM + JDK 17 + Maven + Kotlin 2.4.0.

If you would like to learn more about Kronos, please visit [Kronos](https://www.kotlinorm.com/).

## NOTICE
**If you built failed in Intellij IDEA and build with Maven, please try to enable the following setting: Settings / Build, Execution, Deployment / Build Tools / Maven / Runner / Delegate IDE build/run actions to Maven.**

## Introducing Maven dependencies

**1. Add Kronos dependency**

```xml
<dependencies>
    <dependency>
        <groupId>com.kotlinorm</groupId>
        <artifactId>kronos-core</artifactId>
        <version>0.2.3</version>
    </dependency>
    <dependency>
        <groupId>com.kotlinorm</groupId>
        <artifactId>kronos-jdbc-wrapper</artifactId>
        <version>0.2.3</version>
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
                <version>0.2.3</version>
            </dependency>
        </dependencies>
    </plugin>
</plugins>
```

## Configure the data source

The sample project uses kronos-jdbc-wrapper, and you can configure the data source in the following way.
You can also replace it with another wrapper or customize the wrapper.

```kotlin
import com.kotlinorm.Kronos
import com.kotlinorm.wrappers.KronosJdbcWrapper
import org.apache.commons.dbcp2.BasicDataSource

@SolonMain
class App

@Inject("\${db.url}")
var dbUrl: String? = null

@Inject("\${db.username}")
var dbUsername: String? = null

@Inject("\${db.password}")
var dbPassword: String? = null

val ds by lazy {
    BasicDataSource().apply {
        driverClassName = System.getenv("MYSQL_DRIVER") ?: "com.mysql.cj.jdbc.Driver"
        url = System.getenv("MYSQL_JDBC_URL") ?: dbUrl ?: "jdbc:mysql://localhost:3306/kronos_testing"
        username = System.getenv("MYSQL_USERNAME") ?: dbUsername ?: "kronos"
        password = System.getenv("MYSQL_PASSWORD") ?: dbPassword ?: "kronos"
    }
}

val pool by lazy { KronosJdbcWrapper(ds) }

fun configureKronos() {
    with(Kronos) {
        dataSource = { pool }
        fieldNamingStrategy = lineHumpNamingStrategy
        tableNamingStrategy = lineHumpNamingStrategy
    }
}

fun main(args: Array<String>) {
    configureKronos()
    Solon.start(App::class.java, args)
}
```

## Run the project

After running the project, visit the following URL to view the results:

```
http://localhost:8082
```

If the interface returns the results shown below, Kronos has run successfully and the compiler plugin is working
properly.

![screen](https://github.com/Kronos-orm/kronos-example-solon/blob/main/screenshot/img.png?raw=true)
