package com.kotlinorm.example.solon

import com.kotlinorm.Kronos
import com.kotlinorm.KronosBasicWrapper
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
        url = dbUrl
        username = dbUsername
        password = dbPassword
    }
}

fun main(args: Array<String>) {
    Kronos.init {
        dataSource = { KronosBasicWrapper(ds) }
        fieldNamingStrategy = lineHumpNamingStrategy
        tableNamingStrategy = lineHumpNamingStrategy
    }
    Solon.start(App::class.java, args)
}