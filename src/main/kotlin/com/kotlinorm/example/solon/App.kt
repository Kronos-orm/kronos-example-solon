package com.kotlinorm.example.solon

import com.kotlinorm.Kronos
import com.kotlinorm.wrappers.KronosJdbcWrapper
import org.apache.commons.dbcp2.BasicDataSource
import org.noear.solon.Solon
import org.noear.solon.annotation.Inject
import org.noear.solon.annotation.SolonMain

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
