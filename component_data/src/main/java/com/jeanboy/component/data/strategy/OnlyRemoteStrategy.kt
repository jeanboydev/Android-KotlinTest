package com.jeanboy.component.data.strategy

import androidx.lifecycle.LiveData
import com.jeanboy.component.data.core.BaseStrategy

/**
 *
 * @author caojianbo
 * @since 2020/1/13 17:52
 */
abstract class OnlyRemoteStrategy<Result> : BaseStrategy<Result>() {

    init {
        toFetch()
    }

    override fun isAutoCache(): Boolean {
        return true
    }

    override fun loadFromLocal(): LiveData<Result>? {
        return null
    }

    override fun saveToLocal(result: Result) {

    }

    override fun shouldFetch(result: Result?): Boolean {
        return true
    }
}