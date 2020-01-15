package com.jeanboy.component.data.strategy

import com.jeanboy.component.data.core.BaseStrategy
import com.jeanboy.component.data.core.Wrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 *
 * @author caojianbo
 * @since 2020/1/13 17:35
 */
abstract class CachePriorityStrategy<Result> : BaseStrategy<Result>() {

    init {
        runBlocking {
            val localData = withContext(Dispatchers.IO) {
                loadFromLocal()
            }

            localData?.value?.let {
                if (!shouldFetch(it)) {
                    resultData.value = Wrapper.successful(it)
                }
            }
            toFetch()
        }
    }

    override fun isAutoCache(): Boolean {
        return true
    }
}