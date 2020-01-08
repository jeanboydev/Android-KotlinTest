package com.jeanboy.module.demo.singleton

/**
 *
 * @author caojianbo
 * @since 2020/1/8 15:47
 */

class Singleton2 private constructor() {
    companion object {
        private var instance: Singleton2? = null
            get() {
                if (field == null) {
                    field = Singleton2()
                }
                return field
            }

        fun get(): Singleton2 {
            return instance!!
        }
    }
}