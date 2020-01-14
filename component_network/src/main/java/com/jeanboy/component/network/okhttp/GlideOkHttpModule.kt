package com.jeanboy.component.network.okhttp

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import okhttp3.OkHttpClient
import java.io.InputStream

/**
 *
 * @author caojianbo
 * @since 2020/1/13 20:05
 */
@GlideModule
class GlideOkHttpModule : AppGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val builder = OkHttpClient.Builder()
        val okHttpClient = builder.build()
        registry.replace(
            GlideUrl::class.java,
            InputStream::class.java,
            OkHttpUrlLoader.Factory(okHttpClient)
        )
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}