package com.jeanboy.component.data.core

import androidx.lifecycle.MediatorLiveData
import com.jeanboy.component.data.handler.LocalHandler
import com.jeanboy.component.data.handler.RemoteConvertHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 *
 * @author caojianbo
 * @since 2020/1/13 17:05
 */
abstract class BaseConvertStrategy<Response, Result> : LocalHandler<Result>,
    RemoteConvertHandler<Response, Result> {

    protected val resultData: MediatorLiveData<Wrapper<Result>> = MediatorLiveData()

    fun asLiveData(): MediatorLiveData<Wrapper<Result>> {
        return resultData
    }

    fun toCommit(result: Result?) {
        result?.let {
            GlobalScope.launch(Dispatchers.IO) {
                saveToLocal(it)
            }
        }
    }

    fun toFetch() {
        resultData.value = Wrapper.loading(null)
        fetchFromRemote(object : Follower<Response> {
            override fun onSuccess(response: Response?) {
                if (response == null) {
                    resultData.value = Wrapper.successful(null)
                    return
                }
                val result = onConvert(response)
                resultData.value = Wrapper.successful(result)
                if (isAutoCache()) {
                    result?.let { toCommit(it) }
                }
            }

            override fun onError(code: Int, msg: String?) {
                onFetchError(code, msg)
            }
        })
    }

    open fun onFetchError(code: Int, msg: String?) {
        resultData.value = Wrapper.error(code, msg)
    }

    abstract fun isAutoCache(): Boolean
}