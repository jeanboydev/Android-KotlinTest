package com.jeanboy.component.data.handler

import androidx.lifecycle.LiveData

/**
 *
 * @author caojianbo
 * @since 2020/1/13 15:22
 */
interface LocalHandler<Result> {

    fun loadFromLocal(): LiveData<Result>?
    fun saveToLocal(result: Result)
}