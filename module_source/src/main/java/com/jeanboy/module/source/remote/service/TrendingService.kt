package com.jeanboy.module.source.remote.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 *
 * @author caojianbo
 * @since 2020/1/14 15:22
 */
interface TrendingService {

    /**
     * https://github-trending-api.now.sh/repositories?language=javascript&since=weekly
     * 获取仓库趋势数据
     */
    @GET("repositories")
    fun getRepositoryTrending(
        @Query("language") language: String,
        @Query("since") since: String
    ): Call<String>
}