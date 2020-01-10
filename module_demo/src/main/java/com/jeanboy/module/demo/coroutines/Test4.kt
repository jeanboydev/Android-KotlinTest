package com.jeanboy.module.demo.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 *
 * @author caojianbo
 * @since 2020/1/10 14:40
 */
class Test4 {
    fun main() {
        GlobalScope.launch {
            val async = async {
                println("running...")
            }

            async.await()
        }

        runBlocking {
            async {

            }
        }
    }
}