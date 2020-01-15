package com.jeanboy.module.test

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alibaba.android.arouter.facade.annotation.Route
import com.jeanboy.component.base.BaseActivity
import com.jeanboy.component.data.core.Wrapper
import com.jeanboy.module.source.viewmodel.UserViewModel
import timber.log.Timber

@Route(path = "/test/activity")
class TestActivity : BaseActivity() {

    private var userViewModel: UserViewModel? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_test
    }

    override fun setupView(savedInstanceState: Bundle?) {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
    }

    override fun setupData() {
        userViewModel?.getLiveData()?.observe(this, Observer<Wrapper<String>> {
            Timber.e("=======onChanged========\n${it.data}")
        })
    }

    fun toRequest(view: View) {
        userViewModel?.get(this)
    }
}
