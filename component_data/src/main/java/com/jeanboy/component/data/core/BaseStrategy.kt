package com.jeanboy.component.data.core

import androidx.lifecycle.MediatorLiveData
import com.jeanboy.component.data.handler.LocalHandler
import com.jeanboy.component.data.handler.RemoteHandler

/**
 *
 * @author caojianbo
 * @since 2020/1/13 15:26
 */
abstract class BaseStrategy<Result> : LocalHandler<Result>, RemoteHandler<Result> {

    protected val resultData: MediatorLiveData<Wrapper<Result>> = MediatorLiveData()

    fun asLiveData(): MediatorLiveData<Wrapper<Result>> {
        return resultData
    }

    fun toCommit(result: Result) {
        DataExecutors.instance.toDisk(Runnable {
            saveToLocal(result)
        })
    }

    fun toFetch() {
        resultData.value = Wrapper.loading(null)
        fetchFromRemote(object : Follower<Result> {
            override fun onSuccess(response: Result) {
                resultData.value = Wrapper.successful(response)
                if (isAutoCache()) {
                    toCommit(response)
                }
            }

            override fun onError(code: Int, msg: String) {
                onFetchError(code, msg)
            }
        })
    }

    open fun onFetchError(code: Int, msg: String) {
        resultData.value = Wrapper.error(code, msg)
    }

    abstract fun isAutoCache(): Boolean
}