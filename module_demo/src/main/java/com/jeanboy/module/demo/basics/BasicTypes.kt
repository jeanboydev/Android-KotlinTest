package com.jeanboy.module.demo.basics

/**
 *
 * @author caojianbo
 * @since 2020/1/7 16:50
 */

// Byte 8
// Short 16
// Int 32
// Long 64
// Float 32
// Double 64

// 运算
// shl(bits) – 有符号左移
// shr(bits) – 有符号右移
// ushr(bits) – 无符号右移
// and(bits) – 位与
// or(bits) – 位或
// xor(bits) – 位异或
// inv() – 位非

// 比较
// 相等性检测：a == b 与 a != b
// 比较操作符：a < b、 a > b、 a <= b、 a >= b
// 区间实例以及区间检测：a..b、 x in a..b、 x !in a..b

// 字符
// Char

// 布尔
// || – 短路逻辑或
// && – 短路逻辑与
// ! - 逻辑非

// 数组
// 数组在 Kotlin 中使用 Array 类来表示，它定义了 get 与 set 函数（按照运算符重载约定这会转变为 []）以及 size 属性，以及一些其他有用的成员函数
val array = Array(5) {}
val array2 =Array<String>(6){
    ""
}

// 字符串
// String
