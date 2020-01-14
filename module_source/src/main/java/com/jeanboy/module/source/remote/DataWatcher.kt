package com.jeanboy.module.source.remote

import com.jeanboy.component.data.core.Follower
import com.jeanboy.component.network.core.Result
import com.jeanboy.component.network.core.Watcher

/**
 *
 * @author caojianbo
 * @since 2020/1/14 12:14
 */
class DataWatcher<T>(private var follower: Follower<T>) : Watcher<Result<T>> {

    override fun onSuccess(response: Result<T>?) {
        follower.onSuccess(response?.body)
    }

    override fun onError(code: Int, msg: String?) {
        follower.onError(code, msg)
    }


}