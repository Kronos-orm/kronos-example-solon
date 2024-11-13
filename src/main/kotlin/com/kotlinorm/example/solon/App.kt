package com.kotlinorm.example.solon

import com.kotlinorm.Kronos
import com.kotlinorm.KronosBasicWrapper
import com.kotlinorm.beans.config.LineHumpNamingStrategy
import org.apache.commons.dbcp2.BasicDataSource
import org.noear.solon.Solon
import org.noear.solon.annotation.SolonMain

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