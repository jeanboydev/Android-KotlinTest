package com.jeanboy.module.demo.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 *
 * @author caojianbo
 * @since 2020/1/8 11:08
 */

fun main() = runBlocking {
    val job = launch {
        repeat(1000) {
            println("job: I'm sleeping $it ...")
            delay(500L)
        }
    }
    delay(1300L)
    println("main： I'm tired of waiting!")
    job.cancel()
    job.join()
    print("main: Now I can quit.")
}