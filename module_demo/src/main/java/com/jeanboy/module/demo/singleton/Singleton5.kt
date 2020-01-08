package com.jeanboy.module.demo.singleton

/**
 *
 * @author caojianbo
 * @since 2020/1/8 15:58
 */
class Singleton5 private constructor() {
    companion object {
        val instance = SingletonHolder.holder
    }

    private object SingletonHolder {
        val holder = Singleton5()
    }
}