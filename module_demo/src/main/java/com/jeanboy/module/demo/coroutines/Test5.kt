package com.jeanboy.module.demo.coroutines

import kotlinx.coroutines.*

/**
 *
 * @author caojianbo
 * @since 2020/1/10 15:30
 */

fun main()  {

    println("I am running in main : ${Thread.currentThread().name}")

    GlobalScope.launch {
        println("I am running in runBlocking : ${Thread.currentThread().name}")
        launch {
            println("I am running in launch : ${Thread.currentThread().name}")
            delay(1000L)
            println("launch delay")
        }

        val async = async {
            println("I am running in async : ${Thread.currentThread().name}")
            delay(2000L)
            println("async delay")
            "aaa"
        }


        println("start await")

        val await = async.await()

        println("end $await")
    }

    runBlocking {
        delay(5000L)
    }
    println("end main : ${Thread.currentThread().name}")
}
