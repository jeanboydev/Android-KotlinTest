package com.jeanboy.component.network.core

/**
 *
 * @author caojianbo
 * @since 2020/1/13 18:25
 */
interface Machine {

    fun init(config: Configuration)

    fun <T> create(baseUrl: String?, convertType: Int, clazz: Class<T>): T

    fun <T> create(clazz: Class<T>): T
}