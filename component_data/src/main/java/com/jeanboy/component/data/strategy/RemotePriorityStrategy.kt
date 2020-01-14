package com.jeanboy.component.data.strategy

import com.jeanboy.component.data.core.BaseStrategy
import com.jeanboy.component.data.core.Wrapper

/**
 *
 * @author caojianbo
 * @since 2020/1/13 18:01
 */
abstract class RemotePriorityStrategy<Result> : BaseStrategy<Result>() {

    init {
        toFetch()
    }

    override fun onFetchError(code: Int, msg: String) {
        val localData = loadFromLocal()
        localData?.value?.let {
            resultData.value = Wrapper.successful(it)
            return
        }
        super.onFetchError(code, msg)
    }

    override fun isAutoCache(): Boolean {
        return true
    }

    override fun shouldFetch(result: Result?): Boolean {
        return true
    }
}