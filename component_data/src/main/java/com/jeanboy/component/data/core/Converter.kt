package com.jeanboy.component.data.core

/**
 *
 * @author caojianbo
 * @since 2020/1/13 14:38
 */
abstract class Converter<From, To> {

    abstract fun transform(from: From): To

    fun transform(fromList: List<From>?): List<To>? {
        if (fromList == null) return null
        val toList: MutableList<To> = ArrayList()
        for (from: From in fromList) {
            toList.add(transform(from))
        }
        return toList
    }
}