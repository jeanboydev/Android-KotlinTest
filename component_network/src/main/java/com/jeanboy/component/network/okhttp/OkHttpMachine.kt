package com.jeanboy.component.network.okhttp

import com.jeanboy.component.network.constants.ConvertType
import com.jeanboy.component.network.core.Configuration
import com.jeanboy.component.network.core.Machine
import com.jeanboy.component.network.core.Result
import com.jeanboy.component.network.core.Watcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

/**
 *
 * @author caojianbo
 * @since 2020/1/13 20:14
 */
class OkHttpMachine : Machine {

    private var okHttpClient: OkHttpClient? = null
    private var config: Configuration? = null
    private val retrofitCache: MutableMap<String, Retrofit> = WeakHashMap()
    private val interceptorList: MutableList<Interceptor> = ArrayList()
    private val networkInterceptorList: MutableList<Interceptor> = ArrayList()

    override fun init(config: Configuration) {
        this.config = config
        val loggingInterceptor = HttpLoggingInterceptor()
        if (config.isDebug) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
        }

        val builder =
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(config.connectTimeout, TimeUnit.SECONDS)
                .readTimeout(config.readTimeout, TimeUnit.SECONDS)
                .writeTimeout(config.writeTimeout, TimeUnit.SECONDS)
        if (interceptorList.isNotEmpty()) {
            for (interceptor in interceptorList) {
                builder.addInterceptor(interceptor)
            }
        }
        if (networkInterceptorList.isNotEmpty()) {
            for (interceptor in networkInterceptorList) {
                builder.addInterceptor(interceptor)
            }
        }
        okHttpClient = builder.build()
    }

    override fun <T> create(baseUrl: String?, convertType: Int, clazz: Class<T>): T {
        val retrofit = create(baseUrl, convertType)
        return retrofit.create(clazz)
    }

    override fun <T> create(clazz: Class<T>): T {
        if (config == null) {
            throw IllegalArgumentException("Configuration is null!")
        }
        return create(config!!.getHost(), config!!.convertType, clazz)
    }

    fun <T> request(call: Call<T>, watcher: Watcher<Result<T>>) {
        OkHttpProcessor<T>().request(call, watcher)
    }

    fun <T> requestSync(call: Call<T>, watcher: Watcher<Result<T>>) {
        OkHttpProcessor<T>().requestSync(call, watcher)
    }

    fun addInterceptor(interceptor: Interceptor) {
        interceptorList.add(interceptor)
    }

    fun addNetworkInterceptor(interceptor: Interceptor) {
        networkInterceptorList.add(interceptor)
    }

    private fun create(baseUrl: String?, convertType: Int): Retrofit {
        if (baseUrl == null) {
            throw RuntimeException("Host is null!")
        }
        val key = getKey(baseUrl, convertType)
        var retrofit = retrofitCache[key]
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(getFactory(convertType))
                .client(okHttpClient!!)
                .build()
            retrofitCache[key] = retrofit
        }
        return retrofit!!
    }

    private fun getKey(baseUrl: String?, convertType: Int): String {
        return baseUrl + convertType
    }

    private fun getFactory(type: Int): Converter.Factory {
        if (type == ConvertType.JSON) {
            return GsonConverterFactory.create()
        }
        return ScalarsConverterFactory.create()
    }
}