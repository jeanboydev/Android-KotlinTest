package com.jeanboy.component.data.handler

import com.jeanboy.component.data.core.Follower

/**
 *
 * @author caojianbo
 * @since 2020/1/13 15:24
 */
interface RemoteHandler<Result> {

    fun shouldFetch(result: Result?): Boolean
    fun fetchFromRemote(follower: Follower<Result>)
}