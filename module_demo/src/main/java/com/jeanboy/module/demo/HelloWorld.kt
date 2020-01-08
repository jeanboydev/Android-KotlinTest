package com.jeanboy.module.demo

/**
 *
 * @author caojianbo
 * @since 2020/1/7 16:20
 */

fun main() {
    print("Hello World!")
}

// 函数

// 带有两个 Int 参数、返回 Int 的函数
fun sum(a: Int, b: Int): Int {
    return a + b
}

// 将表达式作为函数体、返回值类型自动推断的函数
fun sum2(a: Int, b: Int) = a + b

// 函数返回无意义的值
fun print1(a: Int, b: Int): Unit {
    println(" haha ")
}

// Unit 返回类型可以省略
fun print2(a: Int, b: Int) {
    println("hehe")
}

// 变量
// 定义只读局部变量使用关键字 val 定义。只能为其赋值一次
val a: Int = 1 // 立即赋值，不可再赋值
val b = 2 // 自动推断出 Int 类型
var x = 5 // 可重新赋值

// 字符串模板

var aa = 1
val s1 = "aa is $aa"

// 空值与 null 检测
// 当某个变量的值可以为 null 的时候，必须在声明处的类型后添加 ? 来标识该引用可为空
fun parseInt(str: String): Int? {
    return null
}

// when 表达式
fun describe(obj: Any): String =
    when (obj) {
        1 -> "one"
        "Hello" -> "Greeting"
        is Long -> "Long"
        else -> "Unknown"
    }

// 使用区间（range）

fun rang() {
    val x1 = 10
    val y1 = 9

    if (x1 in 1..y1 + 1) {

    }

    for (x in 1..5) {

    }

    for (x in 1..10 step 2) {

    }
}