package com.jeanboy.component.base

import android.app.Application

/**
 *
 * @author caojianbo
 * @since 2020/1/13 11:11
 */
abstract class BaseApplication : Application() {

    private lateinit var instance: BaseApplication

    abstract fun onInitialized()

    override fun onCreate() {
        super.onCreate()
        instance = this
        onInitialized()
    }
}