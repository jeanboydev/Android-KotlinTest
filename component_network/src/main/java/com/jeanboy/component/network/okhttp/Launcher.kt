package com.jeanboy.component.network.okhttp

import com.jeanboy.component.network.core.Machine
import retrofit2.Call

/**
 *
 * @author caojianbo
 * @since 2020/1/13 20:13
 */
interface Launcher<T> {
    fun onAction(machine: Machine): Call<T>
}