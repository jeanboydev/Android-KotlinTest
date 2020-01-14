package com.jeanboy.module.source.viewmodel

import android.content.Context
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.jeanboy.component.data.core.Wrapper
import com.jeanboy.module.source.repository.UserRepository

/**
 *
 * @author caojianbo
 * @since 2020/1/14 15:56
 */
class UserViewModel : ViewModel() {

    private val liveData: MediatorLiveData<Wrapper<String>> = MediatorLiveData()

    fun getLiveData(): MediatorLiveData<Wrapper<String>> {
        return liveData
    }

    fun get(context: Context) {
        val repository = UserRepository.get(context).loadRepositoryTrending()
        liveData.addSource(repository) {
            liveData.value = it
        }
    }
}