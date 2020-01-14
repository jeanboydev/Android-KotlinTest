package com.jeanboy.app.kotlin.base

import com.alibaba.android.arouter.launcher.ARouter
import com.jeanboy.app.kotlin.BuildConfig
import com.jeanboy.component.base.BaseApplication
import com.jeanboy.component.network.NetManager
import com.jeanboy.component.network.constants.ConvertType
import com.jeanboy.component.network.core.Configuration
import com.jeanboy.component.network.okhttp.OkHttpMachine
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 *
 * @author caojianbo
 * @since 2020/1/6 18:38
 */

class MainApplication : BaseApplication() {

    override fun onInitialized() {
        setupARouter()
        setupNetwork()
        setupLogger()
    }

    private fun setupARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

    private fun setupNetwork() {
        val config: Configuration = Configuration.Builder()
            .setDebug(BuildConfig.DEBUG)
            .setConnectTimeout(30)
            .setReadTimeout(30)
            .setWriteTimeout(30)
            .setDevelopHost("https://github-trending-api.now.sh")
            .setProduceHost("https://github-trending-api.now.sh")
            .setConvertType(ConvertType.STRING)
            .create()
        val okHttpMachine = OkHttpMachine()
        NetManager.instance.setConfig(config).setMachine(okHttpMachine).init()
    }
    private fun setupLogger() {
        Timber.plant(DebugTree())
    }
}