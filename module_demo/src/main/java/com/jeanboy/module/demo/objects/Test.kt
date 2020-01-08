package com.jeanboy.module.demo.objects


/**
 *
 * @author caojianbo
 * @since 2020/1/7 17:12
 */

fun main() {
    // Kotlin 并没有 new 关键字
//    val child = Child(2)


    val str = Data("haha") + Data("ooo")
    println(str.value)
}
