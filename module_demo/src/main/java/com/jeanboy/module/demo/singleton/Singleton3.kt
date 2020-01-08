package com.jeanboy.module.demo.singleton

/**
 *
 * @author caojianbo
 * @since 2020/1/8 15:50
 */
class Singleton3 private constructor() {
    companion object {
        private var instance: Singleton3? = null
            get() {
                if (field == null) {
                    field = Singleton3()
                }
                return field
            }

        @Synchronized
        fun get(): Singleton3 {
            return instance!!
        }
    }
}