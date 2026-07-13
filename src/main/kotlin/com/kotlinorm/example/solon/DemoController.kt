package com.kotlinorm.example.solon

import com.kotlinorm.example.solon.pojos.User
import org.noear.solon.annotation.Controller
import org.noear.solon.annotation.Mapping

@Controller
class DemoController {
    @Mapping("/")
    fun index(): Map<String, Any> {
        val user = User()
        return mapOf(
            "title" to "Hello, this is Solon & Kronos ORM",
            "tableName" to user.__tableName,
            "columns" to user.__columns.map {
                mapOf(
                    "name" to it.name,
                    "type" to it.type,
                    "nullable" to it.nullable,
                    "primaryKey" to it.primaryKey.name,
                    "comment" to it.kDoc
                )
            }
        )
    }

    @Mapping("/hello")
    fun hello(): String {
        val tableName = User().__tableName
        return "hello, $tableName"
    }
}
