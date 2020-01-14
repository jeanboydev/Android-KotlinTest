package com.jeanboy.module.source.remote.launcher

import com.jeanboy.component.network.core.Machine
import com.jeanboy.component.network.okhttp.Launcher
import com.jeanboy.module.source.remote.service.TrendingService
import retrofit2.Call

/**
 *
 * @author caojianbo
 * @since 2020/1/14 15:24
 */
class TrendingLauncher : Launcher<String> {

    private var language: String
    private var since: String

    constructor(language: String, since: String) {
        this.language = language
        this.since = since
    }


    override fun onAction(machine: Machine): Call<String> {
        val service = machine.create(TrendingService::class.java)
        return service.getRepositoryTrending(language, since)
    }
}