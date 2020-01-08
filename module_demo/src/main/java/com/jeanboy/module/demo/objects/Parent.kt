package com.jeanboy.module.demo.objects

/**
 *
 * @author caojianbo
 * @since 2020/1/7 17:02
 */
// Kotlin 中使用关键字 class 声明类
// 默认无法继承，添加 open 关键字可继承
// 主构造函数：class Parent constructor(age: Int)
// 如果一个非抽象类没有声明任何（主或次）构造函数，它会有一个生成的不带参数的主构造函数。构造函数的可见性是 public。
// 如果你不希望你的类有一个公有构造函数，你需要声明一个带有非默认可见性的空的主构造函数
open class Parent constructor(age: Int) {
    var name = "Parent"

    init { // 初始化块，初始化块中的代码实际上会成为主构造函数的一部分
        print("Parent initializer")
    }

    // 次构造函数
    // 如果类有一个主构造函数，每个次构造函数需要委托给主构造函数， 可以直接委托或者通过别的次构造函数间接委托
    // 委托给主构造函数会作为次构造函数的第一条语句，因此所有初始化块与属性初始化器中的代码都会在次构造函数体之前执行
    constructor(name: String, age: Int) : this(age) {
        this.name = name
    }

    open fun test() {

    }
}