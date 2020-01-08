package com.jeanboy.module.demo.singleton

/**
 *
 * @author caojianbo
 * @since 2020/1/8 15:53
 */

class Singleton4 private constructor() {
    companion object {
        val instance: Singleton4 by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            Singleton4()
        }
    }
}