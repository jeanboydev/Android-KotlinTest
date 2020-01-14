package com.jeanboy.component.network.core

/**
 *
 * @author caojianbo
 * @since 2020/1/13 18:36
 */
interface Watcher<Response> {

    fun onSuccess(response: Response?)

    fun onError(code: Int, msg: String?)
}