package com.jeanboy.component.data.strategy

import com.jeanboy.component.data.core.BaseStrategy
import com.jeanboy.component.data.core.Wrapper

/**
 *
 * @author caojianbo
 * @since 2020/1/13 17:35
 */
abstract class CachePriorityStrategy<Result> : BaseStrategy<Result>() {

    init {
        val localData = this.loadFromLocal()
        localData?.value?.let {
            if (!this.shouldFetch(it)) {
                resultData.value = Wrapper.successful(it)
            }
        }
        toFetch()
    }

    override fun isAutoCache(): Boolean {
        return true
    }
}