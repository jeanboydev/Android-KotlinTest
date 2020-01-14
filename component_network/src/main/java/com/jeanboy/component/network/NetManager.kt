package com.jeanboy.component.network

import com.jeanboy.component.network.core.Configuration
import com.jeanboy.component.network.core.Machine
import com.jeanboy.component.network.core.Result
import com.jeanboy.component.network.core.Watcher
import com.jeanboy.component.network.okhttp.Launcher
import com.jeanboy.component.network.okhttp.OkHttpMachine

/**
 *
 * @author caojianbo
 * @since 2020/1/13 18:10
 */
class NetManager private constructor() {

    private var config: Configuration? = null
    private var machine: Machine? = null

    companion object {
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            NetManager()
        }
    }

    fun setConfig(config: Configuration): NetManager {
        this.config = config
        return this
    }

    fun setMachine(machine: Machine): NetManager {
        this.machine = machine
        return this
    }

    fun init() {
        if (machine == null) {
            throw IllegalArgumentException("You cannot initialize on a null machine.")
        }

        if (config == null) {
            config = Configuration.Builder().create()
        }

        machine!!.init(config!!)
    }

    fun <T> request(launcher: Launcher<T>, watcher: Watcher<Result<T>>) {
        if (machine == null) {
            throw IllegalArgumentException("Machine is null.")
        }

        if (machine is OkHttpMachine) {
            val okHttpMachine = machine as OkHttpMachine
            okHttpMachine.request(launcher.onAction(machine!!), watcher)
        }
    }
}