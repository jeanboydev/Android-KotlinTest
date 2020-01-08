package com.jeanboy.module.demo.objects

/**
 *
 * @author caojianbo
 * @since 2020/1/8 18:16
 */

class Data(var value: String) {

    operator fun plus(other: Data): Data {
        this.value = this.value + "_**_" + other.value
        return this
    }
}