package com.jeanboy.module.demo.objects

/**
 *
 * @author caojianbo
 * @since 2020/1/7 17:03
 */

class Child(age: Int) : Parent(age), User {

    override fun test() {

    }

    fun test2() {
        super.test()
    }

    inner class Filter {
        fun test() {

        }
    }

    // 伴生对象，该伴生对象的成员可通过只使用类名作为限定符来调用
    companion object {
        const val STATIC = "常量"
    }

    // 延迟初始化属性与变量
    lateinit var username: String


}