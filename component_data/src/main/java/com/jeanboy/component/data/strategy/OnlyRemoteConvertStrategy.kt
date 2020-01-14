package com.jeanboy.component.data.strategy

import androidx.lifecycle.LiveData
import com.jeanboy.component.data.core.BaseConvertStrategy

/**
 *
 * @author caojianbo
 * @since 2020/1/13 17:49
 */
abstract class OnlyRemoteConvertStrategy<Response, Result> :
    BaseConvertStrategy<Response, Result>() {

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