package com.jeanboy.app.kotlin.base

import android.app.Application
import kotlin.properties.Delegates

/**
 *
 * @author caojianbo
 * @since 2020/1/6 18:38
 */

class MainApplication : Application() {

    companion object {
        private var instance: MainApplication by Delegates.notNull()
        fun instance() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}