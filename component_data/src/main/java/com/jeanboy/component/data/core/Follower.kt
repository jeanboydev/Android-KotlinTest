package com.jeanboy.component.data.core

/**
 *
 * @author caojianbo
 * @since 2020/1/13 12:11
 */
interface Follower<Response> {

    fun onSuccess(response: Response?)
    fun onError(code: Int, msg: String?)
}