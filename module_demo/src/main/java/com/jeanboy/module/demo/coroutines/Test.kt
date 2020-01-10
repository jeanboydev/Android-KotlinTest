package com.jeanboy.module.demo.coroutines

import kotlinx.coroutines.*

/**
 *
 * @author caojianbo
 * @since 2020/1/8 10:15
 */
class Test {
    fun main() {
        GlobalScope.launch {
            delay(1000L)
            print("World!")
        }
        println("Hello")
        // 阻塞当前线程来等待
        runBlocking {
            delay(2000L) // delay 是非阻塞的
        }
    }
}
