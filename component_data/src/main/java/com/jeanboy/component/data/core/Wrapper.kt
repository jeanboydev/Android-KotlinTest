package com.jeanboy.component.data.core

import com.jeanboy.component.data.constants.DataState

/**
 *
 * @author caojianbo
 * @since 2020/1/13 12:12
 */
data class Wrapper<Data>(
    val status: Int,
    val data: Data? = null,
    val code: Int = 0,
    val msg: String? = null
) {

    fun isLoading(): Boolean {
        return status == DataState.LOADING
    }

    fun isSuccessful(): Boolean {
        return status == DataState.SUCCESSFUL
    }

    fun isError(): Boolean {
        return status == DataState.ERROR
    }

    companion object {
        fun <Data> loading(data: Data?): Wrapper<Data>? {
            return Wrapper(DataState.LOADING, data, 0, null)
        }

        fun <Data> successful(data: Data?): Wrapper<Data> {
            return Wrapper(DataState.SUCCESSFUL, data, 0, null)
        }

        fun <Data> error(code: Int, msg: String?): Wrapper<Data> {
            return Wrapper(DataState.ERROR, null, code, msg)
        }
    }
}