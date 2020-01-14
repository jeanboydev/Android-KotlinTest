package com.jeanboy.module.source.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.jeanboy.component.data.core.Follower
import com.jeanboy.component.data.core.Wrapper
import com.jeanboy.component.data.strategy.CachePriorityStrategy
import com.jeanboy.component.network.NetManager
import com.jeanboy.module.source.database.AppDatabase
import com.jeanboy.module.source.database.dao.UserDao
import com.jeanboy.module.source.remote.DataWatcher
import com.jeanboy.module.source.remote.launcher.TrendingLauncher

/**
 *
 * @author caojianbo
 * @since 2020/1/14 15:27
 */
class UserRepository(context: Context) {

    private var context: Context = context.applicationContext
    private var userDao: UserDao = AppDatabase.get(context).userDao()

    companion object {
        private var instance: UserRepository? = null

        fun get(context: Context): UserRepository {
            if (instance == null) {
                synchronized(UserRepository::class) {
                    if (instance == null) {
                        instance = UserRepository(context)
                    }
                }
            }
            return instance!!
        }
    }

    fun loadRepositoryTrending(): LiveData<Wrapper<String>> {
        return object : CachePriorityStrategy<String>() {
            override fun loadFromLocal(): LiveData<String>? {
                return null
            }

            override fun saveToLocal(result: String) {
            }

            override fun shouldFetch(result: String?): Boolean {
                return result == null
            }

            override fun fetchFromRemote(follower: Follower<String>) {
                NetManager.instance.request(
                    TrendingLauncher("", ""),
                    DataWatcher(follower)
                )
            }
        }.asLiveData()
    }
}