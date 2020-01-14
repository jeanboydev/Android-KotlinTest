package com.jeanboy.component.data.core

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 *
 * @author caojianbo
 * @since 2020/1/13 14:44
 */
class DataExecutors {

    private lateinit var diskIO: Executor
    private lateinit var mainThread: Executor

    private constructor() {
        DataExecutors(Executors.newSingleThreadExecutor(), MainThreadExecutor())
    }

    private constructor(diskIO: Executor, mainThread: Executor) : this() {
        this.diskIO = diskIO
        this.mainThread = mainThread
    }

    companion object {
        val instance: DataExecutors = SingletonHolder.value
    }

    private object SingletonHolder {
        val value: DataExecutors = DataExecutors()
    }

    fun toMainThread(runnable: Runnable) {
        mainThread.execute(runnable)
    }

    fun toDisk(runnable: Runnable) {
        diskIO.execute(runnable)
    }

    class MainThreadExecutor : Executor {

        private val mainThreadHandler: Handler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }

    }
}