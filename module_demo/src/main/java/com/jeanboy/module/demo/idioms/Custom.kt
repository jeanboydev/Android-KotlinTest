package com.jeanboy.module.demo.idioms

/**
 *
 * @author caojianbo
 * @since 2020/1/7 16:39
 */

// 函数默认参数
fun foo(a: Int = 0, b: String = "") {

}
// 延迟属性
//val p:String by lazy {
//    // 计算该字符串
//}

// 扩展函数
fun String.spaceToCamelCase() {
    // 转为驼峰
}

// 创建单例
object Resource {
    val name = ""
}
