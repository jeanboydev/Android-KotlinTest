package com.jeanboy.component.data.handler

import com.jeanboy.component.data.core.Follower

/**
 *
 * @author caojianbo
 * @since 2020/1/13 15:24
 */
interface RemoteConvertHandler<Response, Result> {

    fun shouldFetch(result: Result?): Boolean = false
    fun fetchFromRemote(follower: Follower<Response>)
    fun onConvert(response: Response): Result?
}