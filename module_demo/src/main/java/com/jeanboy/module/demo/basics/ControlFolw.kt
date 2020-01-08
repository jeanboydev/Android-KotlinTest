package com.jeanboy.module.demo.basics

/**
 *
 * @author caojianbo
 * @since 2020/1/7 16:58
 */

// if 表达式
// 在 Kotlin 中，if是一个表达式，即它会返回一个值。 因此就不需要三元运算符（条件 ? 然后 : 否则），因为普通的 if 就能胜任这个角色

fun test() {
    val a = 10
    val b = 11
    var max = a
    if (a < b) max = b

    max = if (a < b) a else b
}