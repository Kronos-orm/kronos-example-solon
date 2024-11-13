package features

import com.kotlinorm.example.solon.App

import org.junit.jupiter.api.Test

import org.noear.solon.test.HttpTester
import org.noear.solon.test.SolonTest

@SolonTest(App::class)
open class HelloTest : HttpTester() {
    @Test
    open fun hello() {
        assert(path("/hello").get().equals("hello, tb_user"))
    }
}