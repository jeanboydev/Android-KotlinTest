package com.jeanboy.component.network.core

import com.jeanboy.component.network.constants.ConvertType

/**
 *
 * @author caojianbo
 * @since 2020/1/13 18:13
 */
class Configuration private constructor() {

    var isDebug: Boolean = false
    var connectTimeout: Long = 60
    var readTimeout: Long = 120
    var writeTimeout: Long = 120
    var developHost: String? = null
    var produceHost: String? = null
    var convertType: Int = ConvertType.STRING

    fun getHost(): String? {
        return if (isDebug) {
            developHost
        } else {
            produceHost
        }
    }

    class Builder {
        private val config: Configuration = Configuration()
        fun setConnectTimeout(timeout: Long): Builder {
            config.connectTimeout = timeout
            return this
        }

        fun setReadTimeout(timeout: Long): Builder {
            config.readTimeout = timeout
            return this
        }

        fun setWriteTimeout(timeout: Long): Builder {
            config.writeTimeout = timeout
            return this
        }

        fun setDebug(isDebug: Boolean): Builder {
            config.isDebug = isDebug
            return this
        }

        fun setDevelopHost(host: String): Builder {
            config.developHost = host
            return this
        }

        fun setProduceHost(host: String): Builder {
            config.produceHost = host
            return this
        }

        fun setConvertType(type: Int): Builder {
            config.convertType = type
            return this
        }

        fun create(): Configuration {
            return config
        }
    }
}