package com.jeanboy.component.network.okhttp

import android.text.TextUtils
import com.jeanboy.component.network.constants.Code
import com.jeanboy.component.network.core.Result
import com.jeanboy.component.network.core.Watcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *
 * @author caojianbo
 * @since 2020/1/13 20:47
 */
class OkHttpProcessor<T> {

    fun request(call: Call<T>, watcher: Watcher<Result<T>>?) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    val headers = response.headers()
                    val headerMap: MutableMap<String, Any?> = HashMap()
                    for (name in headers.names()) {
                        headerMap[name] = headers[name]
                    }
                    response.body()?.let {
                        val result: Result<T> = Result(response.code(), headerMap, it)
                        watcher?.run {
                            this.onSuccess(result)
                        }
                    }
                } else {
                    var msg: String? = null
                    response.errorBody()?.let {
                        msg = it.string()
                    }
                    if (TextUtils.isEmpty(msg)) {
                        msg = response.message()
                    }
                    watcher?.run {
                        this.onError(response.code(), msg)
                    }
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                watcher?.run {
                    this.onError(Code.UNKNOWN_EXCEPTION, t.localizedMessage)
                }
            }
        })
    }

    fun requestSync(call: Call<T>, watcher: Watcher<Result<T>>?) {
        try {
            val response = call.execute()
            if (response.isSuccessful) {
                val headers = response.headers()
                val headerMap: MutableMap<String, Any?> = HashMap()
                for (name in headers.names()) {
                    headerMap[name] = headers[name]
                }
                response.body()?.let {
                    val result: Result<T> = Result(response.code(), headerMap, it)
                    watcher?.run {
                        this.onSuccess(result)
                    }
                }
            } else {
                var msg: String? = null
                response.errorBody()?.let {
                    msg = it.string()
                }
                if (TextUtils.isEmpty(msg)) {
                    msg = response.message()
                }
                watcher?.run {
                    this.onError(response.code(), msg)
                }
            }
        } catch (e: Exception) {
            watcher?.run {
                this.onError(Code.UNKNOWN_EXCEPTION, e.localizedMessage)
            }
        }
    }
}