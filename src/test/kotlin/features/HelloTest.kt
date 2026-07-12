package features

import com.kotlinorm.example.solon.App
import com.kotlinorm.example.solon.DemoController
import com.kotlinorm.example.solon.configureKronos
import com.kotlinorm.example.solon.pool
import com.kotlinorm.example.solon.pojos.User
import com.kotlinorm.orm.ddl.table
import com.kotlinorm.orm.insert.insert
import com.kotlinorm.orm.select.select

import org.junit.jupiter.api.Test

import org.noear.solon.test.SolonTest
import org.junit.jupiter.api.Assertions.assertEquals

@SolonTest(App::class)
open class HelloTest {
    @Test
    open fun hello() {
        assertEquals("hello, tb_user", DemoController().hello())
    }

    @Test
    open fun kronosCrudUsesMysql() {
        configureKronos()
        pool.table.syncTable<User>()

        val name = "solon-smoke-${System.nanoTime()}"
        User(name = name, age = 18).insert().execute()

        val user = User(name = name).select().where().first<User>()
        assertEquals(name, user.name)
        assertEquals(18, user.age)
    }
}
